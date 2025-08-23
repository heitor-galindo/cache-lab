package com.cache.lab.cache_lab.controller;

import com.cache.lab.cache_lab.model.Product;
import com.cache.lab.cache_lab.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable Long id) {
        return this.productService.getProduct(id);
    }
}
