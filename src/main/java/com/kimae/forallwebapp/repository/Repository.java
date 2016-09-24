package com.kimae.forallwebapp.repository;

import java.util.List;

public interface Repository<K, O> {
    public O findById(K id);
    public List<O> findAll();
    public boolean save(O object);
}
