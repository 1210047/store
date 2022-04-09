package com.nkb.store.service;

import com.nkb.store.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findHotList();

    Product findById(Integer id);

    List<Product> findNewList();
}
