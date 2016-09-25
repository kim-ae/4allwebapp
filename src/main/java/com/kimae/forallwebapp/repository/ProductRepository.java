package com.kimae.forallwebapp.repository;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.kimae.forallwebapp.entity.Product;
import com.kimae.forallwebapp.entity.Products;
import com.kimae.forallwebapp.infrastructure.ForAllWebService;
import com.kimae.forallwebapp.infrastructure.MapFactory;

@Stateless
@LocalBean
public class ProductRepository implements Repository<Integer, Product> {
    
    @EJB
    private ForAllWebService webService;
    private static final String GET_ALL_METHOD = "getProdutos";
    private static final Map<String, String> AUTH_KEY = MapFactory.createOf("authkey", "hello123");
    private List<Product> productsCache;

    @Override
    public Product findById(Integer id) {
        if(productsCache == null){
            findAll();
        }
        for(Iterator<Product> iter = productsCache.iterator() ; iter.hasNext();){
            Product product = iter.next();
            if(product.getSku().equals(id)){
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        try {
            if(productsCache == null){
                productsCache = ((Products)webService.call(GET_ALL_METHOD, Products.class, AUTH_KEY)).getProdutos();
            }
            return productsCache;
        } catch (UnsupportedCharsetException | IOException e) {
            return new ArrayList<>();
        }
    }
    
    public void invalidateCache(){
        productsCache = null;
    }

    @Override
    public boolean save(Product object) {
        throw new UnsupportedOperationException();
        
    }

}
