package com.halibuttruck.decorator;

import com.halibuttruck.model.ItemCardapio;

public class ExtraBacon extends ItemDecorator {

    private static final double PRECO_EXTRA = 6.00;

    public ExtraBacon(ItemCardapio itemBase) {
        super(itemBase);
    }

    @Override
    public double getPreco() {
        return itemBase.getPreco() + PRECO_EXTRA;
    }

    @Override
    public String getDescricao() {
        return itemBase.getDescricao() + " + bacon crocante";
    }
}
