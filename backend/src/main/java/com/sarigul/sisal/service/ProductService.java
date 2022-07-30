package com.sarigul.sisal.service;

import com.sarigul.sisal.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> getAll();
    Product save(Product product);
    Product update(Product product) throws Exception;
    void deleteById(Long id) throws Exception;
}
