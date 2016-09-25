package com.kimae.forallwebapp.entity;

import java.io.Serializable;

public class OrderSaveStatus implements Serializable {

    private static final long serialVersionUID = 1794615121636318379L;
    private Integer codigo;
    private String status;
    private String msg;
    private Integer id_pedido;

    public Integer getCodigo() {
        return codigo;
    }

    public OrderSaveStatus setCodigo(Integer codigo) {
        this.codigo = codigo;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public OrderSaveStatus setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public OrderSaveStatus setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public OrderSaveStatus setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
        return this;
    }
}
