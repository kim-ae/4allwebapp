package com.kimae.forallwebapp.repository;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Iterator;
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
    private List<Product> products;

    @Override
    public Product findById(Integer id) {
        if(products == null){
            findAll();
        }
        for(Iterator<Product> iter = products.iterator() ; iter.hasNext();){
            Product product = iter.next();
            if(product.getSku().equals(id)){
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        WebService ws = WebService.get4AllWebService();
        try {
            if(products == null){
                products = ((Products)ws.call(GET_ALL_METHOD, Products.class, AUTH_KEY)).getProdutos();
            }
            return products;
        } catch (UnsupportedCharsetException | IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean save(Product object) {
        throw new UnsupportedOperationException();
        
    }

}
