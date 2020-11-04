package com.yjh.service;

import com.yjh.pojo.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll() throws Exception;

    void save(Product product) throws Exception;
}
