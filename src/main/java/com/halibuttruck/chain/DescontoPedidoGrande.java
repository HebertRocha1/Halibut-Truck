package com.halibuttruck.chain;

import com.halibuttruck.builder.Pedido;

/**
 * Concede 15% de desconto para pedidos com 5 itens ou mais — incentiva
 * o cliente a levar mais coisas no mesmo pedido.
 */
public class DescontoPedidoGrande extends ManipuladorDesconto {

    private static final int QUANTIDADE_MINIMA = 5;
    private static final double PERCENTUAL = 0.15;

    @Override
    protected boolean aplicavel(Pedido pedido) {
        return pedido.getItens().size() >= QUANTIDADE_MINIMA;
    }

    @Override
    protected double percentualDesconto() {
        return PERCENTUAL;
    }
}
