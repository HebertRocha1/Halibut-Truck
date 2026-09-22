package com.halibuttruck.observer;

import com.halibuttruck.builder.Pedido;
import com.halibuttruck.enums.StatusPedido;

/**
 * Mantém a cozinha avisada sempre que um pedido novo chega ou muda de
 * estágio, para que a produção seja organizada por ordem de chegada.
 */
public class NotificadorCozinha implements ObservadorPedido {

    @Override
    public void aoAtualizarStatus(Pedido pedido, StatusPedido novoStatus) {
        System.out.printf("[Cozinha] Pedido de %s agora está: %s%n",
                pedido.getNomeCliente(), novoStatus.getDescricao());
    }
}
