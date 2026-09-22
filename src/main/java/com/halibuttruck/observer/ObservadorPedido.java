package com.halibuttruck.observer;

import com.halibuttruck.builder.Pedido;
import com.halibuttruck.enums.StatusPedido;

/**
 * Observer: qualquer parte do sistema interessada em saber quando o status
 * de um pedido muda (a cozinha, o painel de senha, o próprio cliente via
 * notificação) implementa essa interface para ser avisada automaticamente.
 */
public interface ObservadorPedido {

    void aoAtualizarStatus(Pedido pedido, StatusPedido novoStatus);
}
