package com.kimae.forallwebapp.repository;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.kimae.forallwebapp.entity.Product;

@Stateless
@LocalBean
public class ProductRepository implements Repository<Integer, Product> {

    @Override
    public Product findById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Product> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(Product object) {
        // TODO Auto-generated method stub
        
    }

}
