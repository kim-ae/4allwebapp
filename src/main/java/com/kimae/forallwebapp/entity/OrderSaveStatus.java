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

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }
}
