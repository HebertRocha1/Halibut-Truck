package com.halibuttruck.builder;

import com.halibuttruck.enums.StatusPedido;
import com.halibuttruck.model.ItemCardapio;
import com.halibuttruck.strategy.FormaPagamento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa um pedido completo do cliente. É montado peça por peça pelo
 * {@link PedidoBuilder} (Builder Pattern), já que um pedido pode ter vários
 * itens, um nome de cliente opcional, observações etc., e construir tudo
 * isso via um construtor telescópico seria confuso e propenso a erro.
 */
public class Pedido {

    private final String nomeCliente;
    private final List<ItemCardapio> itens;
    private final String observacoes;
    private FormaPagamento formaPagamento;
    private StatusPedido status;

    Pedido(String nomeCliente, List<ItemCardapio> itens, String observacoes) {
        this.nomeCliente = nomeCliente;
        this.itens = itens;
        this.observacoes = observacoes;
        this.status = StatusPedido.RECEBIDO;
    }

    public static PedidoBuilder builder() {
        return new PedidoBuilder();
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public List<ItemCardapio> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public String getObservacoes() {
        return observacoes;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public double calcularTotal() {
        return itens.stream().mapToDouble(ItemCardapio::getPreco).sum();
    }

    /**
     * Builder do pedido: permite encadear a adição de itens e observações
     * de forma legível, e só monta o objeto {@link Pedido} de fato no
     * método {@code build()}.
     */
    public static class PedidoBuilder {
        private String nomeCliente = "Cliente balcão";
        private final List<ItemCardapio> itens = new ArrayList<>();
        private String observacoes = "";

        public PedidoBuilder paraCliente(String nomeCliente) {
            this.nomeCliente = nomeCliente;
            return this;
        }

        public PedidoBuilder adicionarItem(ItemCardapio item) {
            this.itens.add(item);
            return this;
        }

        public PedidoBuilder comObservacoes(String observacoes) {
            this.observacoes = observacoes;
            return this;
        }

        public Pedido build() {
            if (itens.isEmpty()) {
                throw new IllegalStateException("Não é possível fechar um pedido sem itens.");
            }
            return new Pedido(nomeCliente, itens, observacoes);
        }
    }
}
