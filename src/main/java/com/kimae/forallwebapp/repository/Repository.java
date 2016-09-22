package com.kimae.forallwebapp.repository;

import java.util.List;

import javax.ejb.Local;

public interface Repository<K, O> {
    public O findById(K id);
    public List<O> findAll();
    public void save(O object);
}
