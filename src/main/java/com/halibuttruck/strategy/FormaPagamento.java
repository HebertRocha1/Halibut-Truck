package com.halibuttruck.strategy;

/**
 * Strategy: define o contrato para as diferentes formas de pagamento
 * aceitas pelo Halibut Truck. Cada implementação concreta decide como
 * processar o valor e pode aplicar sua própria regra (ex.: taxa do cartão).
 */
public interface FormaPagamento {

    /**
     * Processa o pagamento e retorna uma mensagem de confirmação para o
     * cliente.
     */
    String pagar(double valor);
}
