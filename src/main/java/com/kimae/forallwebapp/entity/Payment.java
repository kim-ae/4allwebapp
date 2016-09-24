package com.kimae.forallwebapp.entity;


public class Payment {
    private String pagamento_status;
    private String pagamento_forma;

    private Payment(String pagamento_status, String pagamento_forma) {
        this.pagamento_status = pagamento_status;
        this.pagamento_forma = pagamento_forma;
    }
    
    public static Payment defaultPaymentForm(){
        return new Payment("nao_pago", "dinheiro");
    }

    public String getPagamento_status() {
        return pagamento_status;
    }

    public String getPagamento_forma() {
        return pagamento_forma;
    }
}
