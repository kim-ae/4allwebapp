package com.kimae.forallwebapp.mock;

import com.kimae.forallwebapp.entity.OrderSaveStatus;

public class OrderSaveStatusMock {

    public static OrderSaveStatus errorStatus(){
        return new OrderSaveStatus().setCodigo(0).setId_pedido(0).setMsg("").setStatus("error");
    }
    
    public static OrderSaveStatus okStatus(){
        return new OrderSaveStatus().setCodigo(0).setId_pedido(0).setMsg("").setStatus("OK");
    }
}
