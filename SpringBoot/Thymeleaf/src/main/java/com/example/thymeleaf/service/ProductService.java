package com.example.thymeleaf.service;

import com.example.thymeleaf.entity.Product;

public interface ProductService {
    String saveProduct(Product product);

    Product getProductById(Long id);

    String performAdminTask();

    String performManagerTask();
}