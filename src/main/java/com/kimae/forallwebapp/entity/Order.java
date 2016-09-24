package com.kimae.forallwebapp.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.kimae.forallwebapp.utils.DataFormatter;
import com.kimae.forallwebapp.utils.SerializationFormatter;

public class Order {
    private final Integer id_loja;
    private final String authkey = "hello123";
    private static Integer id_pedido = 0;
    private final Date dthr_criacao = new Date();
    private Date previsao_entrega;
    private Float total = 0f;
    private final Float frete;
    private final Payment pagamento;
    private final List<OrderItem> itens = new ArrayList<>();
    private final Client cliente;
    private static final Integer DELIVERY_TIME = 50; //minutes
    private final DataFormatter formatter;
    private Order(Integer id_loja, Float frete, Payment pagamento, List<OrderItem> itens,DataFormatter formatter, Client cliente) {
        id_pedido++;
        this.id_loja = id_loja;
        this.frete = frete;
        this.pagamento = pagamento;
        this.itens.addAll(itens);
        this.formatter = formatter;
        this.cliente = cliente;
        initilizeTotal();
        initializeTimeToDelivery();
    }
    
    public static Order defaultOrderSerializable(List<OrderItem> itens){
        
        return new Order(1, 5f, Payment.defaultPaymentForm(), itens, new SerializationFormatter(), Client.defaultClient());
    }

    private void initilizeTotal(){
        this.total += this.frete;
        for(OrderItem item : itens){
            this.total += item.getPreco();
        }
    }
    
    private void initializeTimeToDelivery(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.dthr_criacao);
        cal.add(Calendar.MINUTE, DELIVERY_TIME);
        this.previsao_entrega = cal.getTime(); 
    }

    public Integer getId_loja() {
        return id_loja;
    }

    public String getAuthkey() {
        return authkey;
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public String getDthr_criacao() {
        return formatter.formatDate(dthr_criacao);
    }

    public String getPrevisao_entrega() {
        return formatter.formatDate(previsao_entrega);
    }

    public Float getTotal() {
        return total;
    }

    public Float getFrete() {
        return frete;
    }

    public Payment getPagamento() {
        return pagamento;
    }

    public List<OrderItem> getItens() {
        return new ArrayList<>(itens);
    }
    public Client getCliente(){
        return cliente;
    }
}
