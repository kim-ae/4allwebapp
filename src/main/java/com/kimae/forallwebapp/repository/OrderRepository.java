package com.kimae.forallwebapp.repository;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.kimae.forallwebapp.entity.Order;

@Stateless
@LocalBean
public class OrderRepository implements Repository<Integer, Order> {
    
    private static final String SAVE_METHOD = "setPedido";

    @Override
    public Order findById(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Order> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(Order object) {
        
    }

}
