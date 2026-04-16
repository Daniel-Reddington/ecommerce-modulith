package com.learning.ecommerce.product.infrastructure;

import com.learning.ecommerce.product.infrastructure.persistence.ProductEntity;
import com.learning.ecommerce.product.infrastructure.persistence.SpringDataProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProductPersistenceTest {

    @Autowired
    SpringDataProductRepository repository;

    @Test
    void should_save_product_in_database(){
        ProductEntity productEntity = new ProductEntity(
                UUID.randomUUID(),
                "Laptop",
                1_000D
        );

        ProductEntity saved = repository.save(productEntity);

        assertNotNull(saved.getId());
        assertEquals("Laptop", saved.getName());

    }
}
