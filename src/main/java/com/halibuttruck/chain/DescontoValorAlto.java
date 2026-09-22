package com.halibuttruck.chain;

import com.halibuttruck.builder.Pedido;

/**
 * Concede 10% de desconto para pedidos cujo valor total ultrapasse
 * R$ 100,00, mesmo que tenham poucos itens (ex.: itens mais caros).
 */
public class DescontoValorAlto extends ManipuladorDesconto {

    private static final double VALOR_MINIMO = 100.0;
    private static final double PERCENTUAL = 0.10;

    @Override
    protected boolean aplicavel(Pedido pedido) {
        return pedido.calcularTotal() >= VALOR_MINIMO;
    }

    @Override
    protected double percentualDesconto() {
        return PERCENTUAL;
    }
}
