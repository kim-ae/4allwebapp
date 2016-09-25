package com.kimae.forallwebapp.entity;

public class OrderItem {
    private Float preco;
    private Integer quantidade;
    private Integer sku;

    public Float getPreco() {
        return preco;
    }

    public OrderItem setPreco(Float preco) {
        this.preco = preco;
        return this;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public OrderItem setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public Integer getSku() {
        return sku;
    }

    public OrderItem setSku(Integer sku) {
        this.sku = sku;
        return this;
    }

}
