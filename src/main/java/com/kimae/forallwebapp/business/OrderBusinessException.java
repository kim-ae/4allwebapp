package com.kimae.forallwebapp.business;

import com.kimae.forallwebapp.business.model.OrderStatus;

public class OrderBusinessException extends Exception {

    private static final long serialVersionUID = -1535900139770841976L;
    public OrderBusinessException(OrderStatus status){
        super(status.getMessage());
    }

}
