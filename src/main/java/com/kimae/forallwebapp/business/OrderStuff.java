package com.kimae.forallwebapp.business;

import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.kimae.forallwebapp.business.model.OrderStatus;
import com.kimae.forallwebapp.entity.Order;
import com.kimae.forallwebapp.entity.OrderItem;
import com.kimae.forallwebapp.repository.OrderRepository;

@Stateless
public class OrderStuff {

    @Inject
    private OrderRepository repository;
    
    private Random randomGenerator = new Random();
    
    
    public Integer tryToOrder(List<OrderItem> itens) throws OrderBusinessException{
        Order order = createOrder(itens);
        if(!randomGenerator.nextBoolean() || !repository.save(order)){
            throw new OrderBusinessException(OrderStatus.UNEXPECTED_ERROR);
        }
        return order.getId_pedido();
    }
    
    public Order getOrder(Integer id){
        return repository.findById(id);
    }
    
    private Order createOrder(List<OrderItem> itens){
        return Order.defaultOrderSerializable(itens);
    }
    
    //Used only for test pourpose
    void setRandomGenerator(Random rand){
        this.randomGenerator = rand;
    }
}
