package com.kimae.forallwebapp.business.model;

public enum OrderStatus {
    OK(""), UNEXPECTED_ERROR("Aconteceu um erro inexperado :(. Por favor tente novamente");
    
    private final String message;
    private OrderStatus(String message){
        this.message = message;
    }
    
    public String getMessage(){
        return this.message;
    }
}
