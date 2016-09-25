package com.kimae.forallwebapp.repository;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.kimae.forallwebapp.entity.Order;
import com.kimae.forallwebapp.entity.OrderSaveStatus;
import com.kimae.forallwebapp.infrastructure.ForAllWebService;

@Stateless
@LocalBean
public class OrderRepository implements Repository<Integer, Order> {
    @EJB
    private ForAllWebService webService;
    private static final String SAVE_METHOD = "setPedido";
    private Map<Integer, Order> orders = new HashMap<>();

    @Override
    public Order findById(Integer id) {
        return orders.get(id);
    }

    @Override
    public List<Order> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean save(Order order) {
        boolean saved = false;
        try {
            OrderSaveStatus status = (OrderSaveStatus)webService.call(SAVE_METHOD, OrderSaveStatus.class, order);
            saved = status.getStatus().equals("OK");
            if(saved){
                orders.put(order.getId_pedido(), order);
            }
        } catch (UnsupportedCharsetException | IOException e) {
            //TODO: REMOVE THIS PLS.
            orders.put(order.getId_pedido(), order);
            return true;
        }
        return saved;
    }

}
