package com.oshcherbak.service;

import java.util.List;

public interface Service<T> {
    T findOne(long id);
    List<T> findAll();
    void save(T ob);
    void delete(T ob);
    void update(T ob);
}
