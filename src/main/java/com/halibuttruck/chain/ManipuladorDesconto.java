package com.halibuttruck.chain;

import com.halibuttruck.builder.Pedido;

/**
 * Chain of Responsibility: cada manipulador decide, de forma independente,
 * se sabe aplicar um desconto para aquele pedido. Se não souber, repassa
 * para o próximo da cadeia. Isso evita um bloco gigante de if/else com
 * todas as regras de desconto misturadas em um único lugar.
 */
public abstract class ManipuladorDesconto {

    private ManipuladorDesconto proximo;

    public ManipuladorDesconto proximo(ManipuladorDesconto proximo) {
        this.proximo = proximo;
        return proximo;
    }

    /**
     * Calcula o percentual de desconto (ex.: 0.10 = 10%) aplicável a este
     * pedido. Se este manipulador não se aplicar, repassa a decisão adiante.
     */
    public double calcularDesconto(Pedido pedido) {
        if (aplicavel(pedido)) {
            return percentualDesconto();
        }
        if (proximo != null) {
            return proximo.calcularDesconto(pedido);
        }
        return 0.0;
    }

    protected abstract boolean aplicavel(Pedido pedido);

    protected abstract double percentualDesconto();
}
