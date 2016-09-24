package com.kimae.forallwebapp.controller.model;

import com.kimae.forallwebapp.entity.OrderItem;
import com.kimae.forallwebapp.entity.Product;

public class ViewItem {
    
    private final String name;
    private final Integer quantity;
    
    private ViewItem(String name, Integer quantity){
        this.name = name;
        this.quantity = quantity;
    }
    public static ViewItem convertFrom(Product product, OrderItem orderItem){
        return new ViewItem(product.getNome(), orderItem.getQuantidade());
    }
    
    public String getName(){
        return name;
    }
    
    public Integer getQuantity(){
        return quantity;
    }
}
