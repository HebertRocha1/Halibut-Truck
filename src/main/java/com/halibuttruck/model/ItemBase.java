package com.halibuttruck.model;

/**
 * Implementação base para os itens "de verdade" do cardápio (os pratos e
 * bebidas que a fábrica cria). Cada subclasse concreta representa um produto
 * específico do Halibut Truck.
 */
public abstract class ItemBase implements ItemCardapio {

    private final String nome;
    private final double preco;

    protected ItemBase(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public double getPreco() {
        return preco;
    }

    @Override
    public String getDescricao() {
        return nome;
    }

    @Override
    public String toString() {
        return String.format("%s - R$ %.2f", getDescricao(), getPreco());
    }
}
