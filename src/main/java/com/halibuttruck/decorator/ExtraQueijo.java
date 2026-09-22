package com.halibuttruck.decorator;

import com.halibuttruck.model.ItemCardapio;

public class ExtraQueijo extends ItemDecorator {

    private static final double PRECO_EXTRA = 4.00;

    public ExtraQueijo(ItemCardapio itemBase) {
        super(itemBase);
    }

    @Override
    public double getPreco() {
        return itemBase.getPreco() + PRECO_EXTRA;
    }

    @Override
    public String getDescricao() {
        return itemBase.getDescricao() + " + queijo extra";
    }
}
