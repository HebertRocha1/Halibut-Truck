package com.halibuttruck.observer;

import com.halibuttruck.builder.Pedido;
import com.halibuttruck.enums.StatusPedido;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject do Observer Pattern: é quem efetivamente muda o status de um
 * pedido e, ao fazer isso, avisa todos os observadores inscritos
 * (cozinha, painel do cliente, etc.) sem precisar conhecê-los em detalhe.
 */
public class GerenciadorPedido {

    private final List<ObservadorPedido> observadores = new ArrayList<>();

    public void inscrever(ObservadorPedido observador) {
        observadores.add(observador);
    }

    public void desinscrever(ObservadorPedido observador) {
        observadores.remove(observador);
    }

    public void atualizarStatus(Pedido pedido, StatusPedido novoStatus) {
        pedido.setStatus(novoStatus);
        for (ObservadorPedido observador : observadores) {
            observador.aoAtualizarStatus(pedido, novoStatus);
        }
    }
}
