package com.learning.ecommerce.product.domain.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ProductNotFoundException extends RuntimeException {

    private final UUID id;

    public ProductNotFoundException(UUID id) {
        super("Product with id " + id + " not found");
        this.id = id;
    }

}
