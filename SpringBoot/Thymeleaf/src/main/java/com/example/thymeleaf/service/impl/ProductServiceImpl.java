package com.example.thymeleaf.service.impl;

import com.example.thymeleaf.entity.Product;
import com.example.thymeleaf.repository.ProductRepository;
import com.example.thymeleaf.service.ProductService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public String saveProduct(Product product) {
        productRepository.save(product);
        return "Sản phẩm đã được lưu thành công!";
    }

    @Override
    @PostAuthorize("returnObject.owner.username == authentication.name or hasRole('ADMIN')")
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public String performAdminTask() {
        return "Tác vụ chỉ dành cho Admin đã được thực hiện!";
    }

    @Override
    @RolesAllowed("MANAGER")
    public String performManagerTask() {
        return "Tác vụ của Manager đã được thực hiện!";
    }
}