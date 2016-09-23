package com.kimae.forallwebapp.repository;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.kimae.forallwebapp.entity.Product;
import com.kimae.forallwebapp.entity.Products;
import com.kimae.forallwebapp.infrastructure.MapFactory;
import com.kimae.forallwebapp.infrastructure.WebService;

@Stateless
@LocalBean
public class ProductRepository implements Repository<Integer, Product> {
    
    private static final String GET_ALL_METHOD = "getProdutos";
    private static final Map<String, String> AUTH_KEY = MapFactory.createOf("authkey", "hello123");

    @Override
    public Product findById(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Product> findAll() {
        WebService ws = WebService.get4AllWebService();
        try {
            return ((Products)ws.call(GET_ALL_METHOD, Products.class, AUTH_KEY)).getProdutos();
        } catch (UnsupportedCharsetException | IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void save(Product object) {
        throw new UnsupportedOperationException();
        
    }

}
