package com.sparkequation.spring.trial.api.service;

import com.sparkequation.spring.trial.api.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> GetProducts();

    Product GetProduct(String id);

    Product SaveProduct(Product product);

    void DeleteProduct(String id);
}
