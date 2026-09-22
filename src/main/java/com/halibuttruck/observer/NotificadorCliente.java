package com.halibuttruck.observer;

import com.halibuttruck.builder.Pedido;
import com.halibuttruck.enums.StatusPedido;

/**
 * Simula o aviso que o cliente recebe (ex.: painel de senha ou mensagem)
 * conforme o pedido avança.
 */
public class NotificadorCliente implements ObservadorPedido {

    @Override
    public void aoAtualizarStatus(Pedido pedido, StatusPedido novoStatus) {
        System.out.printf("[Painel do cliente] %s, seu pedido está: %s%n",
                pedido.getNomeCliente(), novoStatus.getDescricao());
    }
}
