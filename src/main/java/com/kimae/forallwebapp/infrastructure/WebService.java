package com.kimae.forallwebapp.infrastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class WebService {

    private final String rootUrl;
    private final ObjectMapper mapper = new ObjectMapper();
    
    private WebService(String rootUrl){
        this.rootUrl = rootUrl;
        initializeObjectMapper();
    }
    
    public Object call(String method, Class<? extends Serializable> returnType, Object parameter) throws UnsupportedCharsetException, ClientProtocolException, IOException{
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost  request = new HttpPost (rootUrl+method);
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Accept", "application/json");
        request.setEntity(new StringEntity(mapper.writeValueAsString(parameter),"UTF-8"));
        return deserializeReturn(client.execute(request), returnType);
    }
    
    public static WebService get4AllWebService(){
        return new WebService("http://homolog.delivery.all4mobile.com.br/api/v1/");
    }
    
    private Object deserializeReturn(HttpResponse response, Class<? extends Serializable> returnType) throws IllegalStateException, IOException{
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent(),
                        Charset.forName("UTF-8")));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return mapper.readValue(result.toString(), returnType);
    }

    private void initializeObjectMapper(){
        this.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        
    }
}
