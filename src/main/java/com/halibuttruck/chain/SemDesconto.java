package com.halibuttruck.chain;

import com.halibuttruck.builder.Pedido;

/**
 * Último elo da cadeia: quando nenhuma regra especial se aplica, o pedido
 * simplesmente não recebe desconto. Mantém a cadeia sempre com um
 * resultado definido, sem precisar de tratamento de null no final.
 */
public class SemDesconto extends ManipuladorDesconto {

    @Override
    protected boolean aplicavel(Pedido pedido) {
        return true;
    }

    @Override
    protected double percentualDesconto() {
        return 0.0;
    }
}
