package com.cache.lab.cache_lab.service;

import com.cache.lab.cache_lab.model.Product;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class ProductService {
    @Cacheable(value = "productId", key = "#id")
    public String getProduct(Long productId) {

        Product product1 = new Product(100L, "rice", BigDecimal.valueOf(5.99));
        Product product2 = new Product(101L, "bacon", BigDecimal.valueOf(2.20));
        Product product3 = new Product(102L, "sugar", BigDecimal.valueOf(2.59));
        Product product4 = new Product(103L, "meat", BigDecimal.valueOf(15.25));
        Product product5 = new Product(104L, "popcorn", BigDecimal.valueOf(1.99));
        Product product6 = new Product(105L, "ice-cream", BigDecimal.valueOf(7.60));

        List<Product> productList = List.of(product1, product2, product3, product4, product5, product6);
        for (Product product : productList) {
            if (product.getId().equals(productId)) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
                return product.getName();
            }
        }
        return "not found";
    }
}
