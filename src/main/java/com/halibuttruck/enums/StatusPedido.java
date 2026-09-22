package com.halibuttruck.enums;

/**
 * Representa os estágios pelos quais um pedido passa dentro do food truck,
 * do momento em que é feito até a entrega ao cliente.
 */
public enum StatusPedido {
    RECEBIDO("Pedido recebido"),
    EM_PREPARO("Em preparo na chapa"),
    PRONTO("Pronto para retirada"),
    ENTREGUE("Entregue ao cliente");

    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
