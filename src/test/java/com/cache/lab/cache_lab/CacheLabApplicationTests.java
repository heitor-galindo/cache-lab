package com.cache.lab.cache_lab;

import com.cache.lab.cache_lab.model.Product;
import com.cache.lab.cache_lab.service.ProductService;
import com.redis.testcontainers.RedisContainer;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class CacheLabApplicationTests {

    @Container
    static RedisContainer redisContainer = new RedisContainer(DockerImageName.parse("redis:7.4.1")).withExposedPorts(RedisContainer.REDIS_PORT);

    @DynamicPropertySource
    static void configureRedis(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", redisContainer::getHost);
        registry.add("spring.data.redis.port", redisContainer::getFirstMappedPort);
    }

    @Autowired
    private ProductService productService;

    @Test
    void shouldReturnProductWhenExists() {
        Product product = this.productService.getProduct(100L);
        assertThat(product).isNotNull();
        assertThat(product.getName()).isEqualTo("rice");
    }

    @Test
    void shouldCacheProductAfterFirstCall() {
        long start = System.currentTimeMillis();
        productService.getProduct(100L); // this should be slow
        long firstCallTime = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        productService.getProduct(100L); // this should be fast
        long secondCallTime = System.currentTimeMillis() - start;

        assert secondCallTime < firstCallTime;
    }

}
