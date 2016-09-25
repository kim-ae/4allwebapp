package com.kimae.forallwebapp.entity;

public class Product {
    private Integer id_produto;
    private Double preco;
    private String detalhes;
    private String nome;
    private Integer sku;
    private String descricao;

    public Double getPreco() {
        return preco;
    }

    public Product setPreco(Double preco) {
        this.preco = preco;
        return this;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public Product setDetalhes(String detalhes) {
        this.detalhes = detalhes;
        return this;
    }

    public Integer getSku() {
        return sku;
    }

    public Product setSku(Integer sku) {
        this.sku = sku;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Product setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public Integer getId_produto() {
        return id_produto;
    }

    public Product setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Product setNome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_produto == null) ? 0 : id_produto.hashCode());
        result = prime * result + ((sku == null) ? 0 : sku.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (id_produto == null) {
            if (other.id_produto != null)
                return false;
        } else if (!id_produto.equals(other.id_produto))
            return false;
        if (sku == null) {
            if (other.sku != null)
                return false;
        } else if (!sku.equals(other.sku))
            return false;
        return true;
    }
}
