package com.halibuttruck.decorator;

import com.halibuttruck.model.ItemCardapio;

public class ExtraMolhoEspecial extends ItemDecorator {

    private static final double PRECO_EXTRA = 2.50;

    public ExtraMolhoEspecial(ItemCardapio itemBase) {
        super(itemBase);
    }

    @Override
    public double getPreco() {
        return itemBase.getPreco() + PRECO_EXTRA;
    }

    @Override
    public String getDescricao() {
        return itemBase.getDescricao() + " + molho especial da casa";
    }
}
