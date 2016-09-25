package com.kimae.forallwebapp.mock;

import java.util.Arrays;

import com.kimae.forallwebapp.entity.Order;
import com.kimae.forallwebapp.entity.OrderItem;

public class OrderMockProvider {
    
    private final Order order;

    public OrderMockProvider(){
        OrderItem item = new OrderItem().setPreco(1f).setQuantidade(1).setSku(1);
        order = Order.defaultOrderSerializable(Arrays.asList(item));
    }
    
    public Order getOrder(){
        return order;
    }
}
