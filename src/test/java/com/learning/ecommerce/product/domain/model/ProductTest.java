package com.learning.ecommerce.product.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void should_create_product_successfully(){
        Product product = Product.create("Laptop", 1000);

        assertEquals("Laptop", product.name());
        assertEquals(1000, product.price());
        assertNotNull(product.id());
    }

    @Test
    public void should_fail_when_name_is_empty(){
        assertThrows(IllegalArgumentException.class, () -> Product.create("", 1000));
    }

    @Test
    public void should_fail_when_price_is_invalid(){
        assertThrows(IllegalArgumentException.class, () -> Product.create("Laptop", -1));
    }
}
