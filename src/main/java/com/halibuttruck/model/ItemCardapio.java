package com.halibuttruck.model;

/**
 * Contrato comum a todo item que pode compor um pedido do Halibut Truck,
 * seja um prato principal criado pela fábrica, seja um extra adicionado
 * por um decorator em cima dele.
 */
public interface ItemCardapio {

    String getNome();

    double getPreco();

    /**
     * Descrição textual completa do item, incluindo eventuais extras
     * acrescentados via Decorator.
     */
    default String getDescricao() {
        return getNome();
    }
}
