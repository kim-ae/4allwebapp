package com.kimae.forallwebapp.infrastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kimae.forallwebapp.entity.Products;

public class WebService {

    private static final String URL = "http://homolog.delivery.all4mobile.com.br/api/v1/getProdutos";
    
    public void execGet(){
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost  request = new HttpPost (URL);
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Accept", "application/json");
        Map<String, String> map = MapFactory.createOf("authkey", "hello123");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            request.setEntity(new StringEntity(mapper.writeValueAsString(map),"UTF-8"));
        } catch (UnsupportedCharsetException | JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            HttpResponse response = client.execute(request);
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(),
                            Charset.forName("UTF-8")));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            Products ps = mapper.readValue(result.toString(), Products.class);
            System.out.println("");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
