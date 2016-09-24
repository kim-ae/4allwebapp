package com.kimae.forallwebapp.entity;

public class Client {

    private final String endereco_rua;
    private final String endereco_numero;
    private final String endereco_cidade;
    private final String endereco_uf;
    private final String endereco_bairro;
    private final String endereco_complemento;
    private final String endereco_cep;
    private final String cliente_nome;
    private final String cliente_celular;
    
    private Client(String endereco_rua, String endereco_numero, String endereco_cidade, String endereco_uf,
            String endereco_bairro, String endereco_complemento, String endereco_cep, String cliente_nome,
            String cliente_celular) {
        super();
        this.endereco_rua = endereco_rua;
        this.endereco_numero = endereco_numero;
        this.endereco_cidade = endereco_cidade;
        this.endereco_uf = endereco_uf;
        this.endereco_bairro = endereco_bairro;
        this.endereco_complemento = endereco_complemento;
        this.endereco_cep = endereco_cep;
        this.cliente_nome = cliente_nome;
        this.cliente_celular = cliente_celular;
    }
    
    public String getEndereco_rua() {
        return endereco_rua;
    }

    public String getEndereco_numero() {
        return endereco_numero;
    }

    public String getEndereco_cidade() {
        return endereco_cidade;
    }

    public String getEndereco_uf() {
        return endereco_uf;
    }

    public String getEndereco_bairro() {
        return endereco_bairro;
    }

    public String getEndereco_complemento() {
        return endereco_complemento;
    }

    public String getEndereco_cep() {
        return endereco_cep;
    }

    public String getCliente_nome() {
        return cliente_nome;
    }

    public String getCliente_celular() {
        return cliente_celular;
    }

    public static Client defaultClient(){
        return new Client("Rua Domingos Moretti", "121", "Canoas", "RS", "Centro", "705", "91900121", "Ana Paula Recowert", "84091111");
    }
}
