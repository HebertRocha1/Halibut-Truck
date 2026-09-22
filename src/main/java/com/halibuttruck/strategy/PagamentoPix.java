package com.halibuttruck.strategy;

public class PagamentoPix implements FormaPagamento {

    @Override
    public String pagar(double valor) {
        return String.format("Pagamento de R$ %.2f confirmado via Pix. Aguardando confirmação do QR Code.", valor);
    }
}
