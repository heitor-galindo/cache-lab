package com.cache.lab.cache_lab.service;

import com.cache.lab.cache_lab.model.Product;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Service
public class ProductService {

    private final Map<Long, Product> productMap = Map.of(
            100L, new Product(100L, "rice", BigDecimal.valueOf(5.99)),
            101L, new Product(101L, "bacon", BigDecimal.valueOf(2.20)),
            102L, new Product(102L, "sugar", BigDecimal.valueOf(2.59)),
            103L, new Product(103L, "meat", BigDecimal.valueOf(15.25)),
            104L, new Product(104L, "popcorn", BigDecimal.valueOf(1.99)),
            105L, new Product(105L, "ice-cream", BigDecimal.valueOf(7.60))
            );

    @Cacheable(value = "products", key = "#productId")
    public Product getProduct(Long productId) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
        return productMap.get(productId);
    }
}
