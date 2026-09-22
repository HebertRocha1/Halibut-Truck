package com.halibuttruck.factory;

import com.halibuttruck.model.FishAndChips;
import com.halibuttruck.model.ItemCardapio;
import com.halibuttruck.model.Limonada;
import com.halibuttruck.model.SorveteFrito;
import com.halibuttruck.model.TacosDePeixe;

/**
 * Factory Method: centraliza a criação dos itens do cardápio, evitando que o
 * restante do sistema (atendente, pedido, etc.) precise saber qual classe
 * concreta instanciar para cada prato. Se um novo item entrar no cardápio,
 * basta adicionar um caso aqui.
 */
public class ItemCardapioFactory {

    public static ItemCardapio criar(TipoItem tipo) {
        switch (tipo) {
            case FISH_AND_CHIPS:
                return new FishAndChips();
            case TACOS_DE_PEIXE:
                return new TacosDePeixe();
            case LIMONADA:
                return new Limonada();
            case SORVETE_FRITO:
                return new SorveteFrito();
            default:
                throw new IllegalArgumentException("Item não cadastrado no cardápio: " + tipo);
        }
    }
}
