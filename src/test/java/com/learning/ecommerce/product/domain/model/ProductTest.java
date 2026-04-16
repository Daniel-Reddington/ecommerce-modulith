package com.learning.ecommerce.product.domain.model;

import com.learning.ecommerce.product.domain.exception.InvalidProductNameException;
import com.learning.ecommerce.product.domain.exception.InvalidProductPriceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void should_create_product_successfully() {

        Product product = Product.create("Laptop", 1000.0);

        assertNotNull(product.id());
        assertEquals("Laptop", product.name());
        assertEquals(1000.0, product.price());
    }

    @Test
    void should_throw_exception_when_name_is_empty() {

        assertThrows(InvalidProductNameException.class, () ->
                Product.create("", 1000.0)
        );
    }

    @Test
    void should_throw_exception_when_price_is_invalid() {

        assertThrows(InvalidProductPriceException.class, () ->
                Product.create("Laptop", -10.0)
        );
    }
}