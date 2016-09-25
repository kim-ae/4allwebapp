package com.kimae.forallwebapp.mock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.kimae.forallwebapp.entity.Product;
import com.kimae.forallwebapp.entity.Products;

public class ProductMockProvider {

    
    private final Integer quantity;
    
    private final List<Product> list = new ArrayList<>();
    
    public ProductMockProvider(Integer quantity){
        this.quantity = quantity;
        initializeList();
    }
    
    public Products getProducts(){
        return new Products().setProdutos(new ArrayList<>(list));
    }
    
    public Product productMock(Integer id){
        for(Iterator<Product> iter = list.iterator() ; iter.hasNext();){
            Product product = iter.next();
            if(product.getSku().equals(id)){
                return product;
            }
        }
        return null;
    }
    
    private void initializeList(){
        for(Integer i =0; i< this.quantity;i++){
            list.add(new Product().setDescricao("Descrição "+i)
                                  .setDetalhes("Detalhes "+i)
                                  .setId_produto(i)
                                  .setNome("Nome "+i)
                                  .setPreco(i.doubleValue())
                                  .setSku(i));
        }
    }
}
