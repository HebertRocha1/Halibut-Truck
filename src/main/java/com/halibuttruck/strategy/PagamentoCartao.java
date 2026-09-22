package com.halibuttruck.strategy;

public class PagamentoCartao implements FormaPagamento {

    private static final double TAXA_MAQUININHA = 0.03; // 3%
    private final String numeroFinalCartao;

    public PagamentoCartao(String numeroFinalCartao) {
        this.numeroFinalCartao = numeroFinalCartao;
    }

    @Override
    public String pagar(double valor) {
        double valorComTaxa = valor * (1 + TAXA_MAQUININHA);
        return String.format(
                "Pagamento de R$ %.2f no cartão final %s (com taxa da maquininha: R$ %.2f).",
                valor, numeroFinalCartao, valorComTaxa);
    }
}
