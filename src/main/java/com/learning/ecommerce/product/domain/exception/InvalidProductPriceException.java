package com.learning.ecommerce.product.domain.exception;

public class InvalidProductPriceException extends RuntimeException {

    public InvalidProductPriceException(String message) {
        super(message);
    }

    public static InvalidProductPriceException invalid(){
        return new InvalidProductPriceException("Product price must be greater than zero");
    }
}
