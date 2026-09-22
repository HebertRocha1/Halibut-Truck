package com.halibuttruck.strategy;

public class PagamentoDinheiro implements FormaPagamento {

    @Override
    public String pagar(double valor) {
        return String.format("Pagamento de R$ %.2f recebido em dinheiro. Confira seu troco!", valor);
    }
}
