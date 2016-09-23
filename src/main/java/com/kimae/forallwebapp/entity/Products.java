package com.kimae.forallwebapp.entity;

import java.io.Serializable;
import java.util.List;

public class Products implements Serializable {
    private static final long serialVersionUID = -3101600671082667947L;
    private List<Product> produtos;

    public List<Product> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Product> produtos) {
        this.produtos = produtos;
    }
    
}
