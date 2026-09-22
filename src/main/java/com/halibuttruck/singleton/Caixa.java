package com.halibuttruck.singleton;

import com.halibuttruck.builder.Pedido;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton: existe apenas um caixa por food truck. Ele centraliza o
 * registro de vendas do dia, então não faz sentido ter mais de uma
 * instância "concorrendo" para somar o faturamento.
 *
 * A instância é criada de forma preguiçosa (lazy) e sincronizada para ser
 * segura em cenário com mais de uma thread atendendo pedidos ao mesmo
 * tempo (ex.: múltiplos atendentes no caixa).
 */
public final class Caixa {

    private static volatile Caixa instancia;

    private final List<Pedido> vendasDoDia = new ArrayList<>();

    private Caixa() {
    }

    public static Caixa getInstancia() {
        if (instancia == null) {
            synchronized (Caixa.class) {
                if (instancia == null) {
                    instancia = new Caixa();
                }
            }
        }
        return instancia;
    }

    public synchronized void registrarVenda(Pedido pedido) {
        vendasDoDia.add(pedido);
    }

    public synchronized double getFaturamentoDoDia() {
        return vendasDoDia.stream().mapToDouble(Pedido::calcularTotal).sum();
    }

    public synchronized int getQuantidadePedidos() {
        return vendasDoDia.size();
    }
}
