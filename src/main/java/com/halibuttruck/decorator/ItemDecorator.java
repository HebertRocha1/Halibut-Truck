package com.halibuttruck.decorator;

import com.halibuttruck.model.ItemCardapio;

/**
 * Decorator abstrato: envolve um {@link ItemCardapio} e permite acrescentar
 * comportamento (preço e descrição extra) sem alterar as classes concretas
 * dos pratos nem precisar criar uma subclasse para cada combinação possível
 * de extras (ex.: Fish and Chips + queijo + bacon).
 */
public abstract class ItemDecorator implements ItemCardapio {

    protected final ItemCardapio itemBase;

    protected ItemDecorator(ItemCardapio itemBase) {
        this.itemBase = itemBase;
    }

    @Override
    public String getNome() {
        return itemBase.getNome();
    }

    @Override
    public double getPreco() {
        return itemBase.getPreco();
    }

    @Override
    public String getDescricao() {
        return itemBase.getDescricao();
    }
}
