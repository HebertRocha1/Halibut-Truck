package com.halibuttruck;

import com.halibuttruck.builder.Pedido;
import com.halibuttruck.chain.DescontoPedidoGrande;
import com.halibuttruck.chain.DescontoValorAlto;
import com.halibuttruck.chain.ManipuladorDesconto;
import com.halibuttruck.chain.SemDesconto;
import com.halibuttruck.decorator.ExtraBacon;
import com.halibuttruck.decorator.ExtraMolhoEspecial;
import com.halibuttruck.decorator.ExtraQueijo;
import com.halibuttruck.enums.StatusPedido;
import com.halibuttruck.factory.ItemCardapioFactory;
import com.halibuttruck.factory.TipoItem;
import com.halibuttruck.model.ItemCardapio;
import com.halibuttruck.observer.GerenciadorPedido;
import com.halibuttruck.observer.NotificadorCliente;
import com.halibuttruck.observer.NotificadorCozinha;
import com.halibuttruck.singleton.Caixa;
import com.halibuttruck.strategy.FormaPagamento;
import com.halibuttruck.strategy.PagamentoCartao;
import com.halibuttruck.strategy.PagamentoPix;


public class Main {

    public static void main(String[] args) {
        separador("HALIBUT TRUCK - Abrindo para o primeiro cliente do dia");

        // ---- Observer: cozinha e cliente ficam de olho no andamento do pedido ----
        GerenciadorPedido gerenciador = new GerenciadorPedido();
        gerenciador.inscrever(new NotificadorCozinha());
        gerenciador.inscrever(new NotificadorCliente());

        // ---- Factory Method: criação dos pratos do cardápio ----
        ItemCardapio fishAndChips = ItemCardapioFactory.criar(TipoItem.FISH_AND_CHIPS);
        ItemCardapio tacos = ItemCardapioFactory.criar(TipoItem.TACOS_DE_PEIXE);
        ItemCardapio limonada = ItemCardapioFactory.criar(TipoItem.LIMONADA);
        ItemCardapio sorvete = ItemCardapioFactory.criar(TipoItem.SORVETE_FRITO);

        // ---- Decorator: cliente pede o Fish and Chips turbinado ----
        ItemCardapio fishAndChipsTurbinado = new ExtraBacon(new ExtraQueijo(fishAndChips));
        ItemCardapio tacosComMolho = new ExtraMolhoEspecial(tacos);

        // ---- Builder: montagem do pedido do cliente ----
        Pedido pedidoJoana = Pedido.builder()
                .paraCliente("Joana")
                .adicionarItem(fishAndChipsTurbinado)
                .adicionarItem(tacosComMolho)
                .adicionarItem(limonada)
                .adicionarItem(sorvete)
                .comObservacoes("Sem cebola no tacos, por favor")
                .build();

        System.out.println("Pedido montado para " + pedidoJoana.getNomeCliente() + ":");
        for (ItemCardapio item : pedidoJoana.getItens()) {
            System.out.println("  - " + item.getDescricao() + String.format(" (R$ %.2f)", item.getPreco()));
        }
        System.out.println("Observações: " + pedidoJoana.getObservacoes());
        System.out.printf("Subtotal: R$ %.2f%n", pedidoJoana.calcularTotal());

        // ---- Chain of Responsibility: verifica se o pedido tem direito a desconto ----
        ManipuladorDesconto cadeiaDesconto = new DescontoPedidoGrande();
        cadeiaDesconto.proximo(new DescontoValorAlto()).proximo(new SemDesconto());

        double percentualDesconto = cadeiaDesconto.calcularDesconto(pedidoJoana);
        double totalComDesconto = pedidoJoana.calcularTotal() * (1 - percentualDesconto);

        if (percentualDesconto > 0) {
            System.out.printf("Desconto aplicado: %.0f%% -> Total final: R$ %.2f%n",
                    percentualDesconto * 100, totalComDesconto);
        } else {
            System.out.println("Nenhum desconto aplicável a este pedido.");
        }

        // ---- Strategy: cliente escolhe como pagar ----
        FormaPagamento pagamentoJoana = new PagamentoCartao("4321");
        pedidoJoana.setFormaPagamento(pagamentoJoana);
        System.out.println(pedidoJoana.getFormaPagamento().pagar(totalComDesconto));

        // ---- Observer em ação: o pedido avança de status e todos são avisados ----
        separador("Acompanhando o preparo do pedido");
        gerenciador.atualizarStatus(pedidoJoana, StatusPedido.EM_PREPARO);
        gerenciador.atualizarStatus(pedidoJoana, StatusPedido.PRONTO);
        gerenciador.atualizarStatus(pedidoJoana, StatusPedido.ENTREGUE);

        // ---- Singleton: caixa único registrando o faturamento do dia ----
        Caixa caixa = Caixa.getInstancia();
        caixa.registrarVenda(pedidoJoana);

        // Segundo cliente do dia, mais simples, pagando em dinheiro
        separador("Segundo cliente do dia");
        Pedido pedidoMarcos = Pedido.builder()
                .paraCliente("Marcos")
                .adicionarItem(ItemCardapioFactory.criar(TipoItem.TACOS_DE_PEIXE))
                .adicionarItem(ItemCardapioFactory.criar(TipoItem.LIMONADA))
                .build();

        pedidoMarcos.setFormaPagamento(new com.halibuttruck.strategy.PagamentoDinheiro());
        System.out.println("Pedido montado para " + pedidoMarcos.getNomeCliente() + ":");
        for (ItemCardapio item : pedidoMarcos.getItens()) {
            System.out.println("  - " + item.getDescricao() + String.format(" (R$ %.2f)", item.getPreco()));
        }
        System.out.printf("Total: R$ %.2f%n", pedidoMarcos.calcularTotal());
        System.out.println(pedidoMarcos.getFormaPagamento().pagar(pedidoMarcos.calcularTotal()));

        gerenciador.atualizarStatus(pedidoMarcos, StatusPedido.EM_PREPARO);
        gerenciador.atualizarStatus(pedidoMarcos, StatusPedido.PRONTO);
        gerenciador.atualizarStatus(pedidoMarcos, StatusPedido.ENTREGUE);

        caixa.registrarVenda(pedidoMarcos);

        // Terceiro cliente: pedido grande, testando o desconto por quantidade de itens
        separador("Terceiro cliente do dia (pedido grande para o grupo)");
        Pedido pedidoGrupo = Pedido.builder()
                .paraCliente("Grupo da faculdade")
                .adicionarItem(ItemCardapioFactory.criar(TipoItem.FISH_AND_CHIPS))
                .adicionarItem(ItemCardapioFactory.criar(TipoItem.FISH_AND_CHIPS))
                .adicionarItem(ItemCardapioFactory.criar(TipoItem.TACOS_DE_PEIXE))
                .adicionarItem(ItemCardapioFactory.criar(TipoItem.LIMONADA))
                .adicionarItem(ItemCardapioFactory.criar(TipoItem.LIMONADA))
                .adicionarItem(ItemCardapioFactory.criar(TipoItem.SORVETE_FRITO))
                .build();

        double descontoGrupo = cadeiaDesconto.calcularDesconto(pedidoGrupo);
        double totalGrupo = pedidoGrupo.calcularTotal() * (1 - descontoGrupo);
        System.out.println("Pedido montado para " + pedidoGrupo.getNomeCliente()
                + " (" + pedidoGrupo.getItens().size() + " itens)");
        System.out.printf("Subtotal: R$ %.2f | Desconto: %.0f%% | Total: R$ %.2f%n",
                pedidoGrupo.calcularTotal(), descontoGrupo * 100, totalGrupo);

        pedidoGrupo.setFormaPagamento(new PagamentoPix());
        System.out.println(pedidoGrupo.getFormaPagamento().pagar(totalGrupo));

        gerenciador.atualizarStatus(pedidoGrupo, StatusPedido.EM_PREPARO);
        gerenciador.atualizarStatus(pedidoGrupo, StatusPedido.PRONTO);
        gerenciador.atualizarStatus(pedidoGrupo, StatusPedido.ENTREGUE);

        caixa.registrarVenda(pedidoGrupo);

        // ---- Fechamento do caixa (Singleton) ----
        separador("Fechando o caixa do dia");
        System.out.println("Total de pedidos atendidos: " + caixa.getQuantidadePedidos());
        System.out.printf("Faturamento do dia: R$ %.2f%n", caixa.getFaturamentoDoDia());
    }

    private static void separador(String titulo) {
        System.out.println();
        System.out.println("=========================================================");
        System.out.println(titulo);
        System.out.println("=========================================================");
    }
}
