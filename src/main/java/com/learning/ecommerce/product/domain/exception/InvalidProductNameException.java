package com.learning.ecommerce.product.domain.exception;

public class InvalidProductNameException extends RuntimeException {

        public InvalidProductNameException(String message) {
            super(message);
        }

        public static InvalidProductNameException empty() {
            return new InvalidProductNameException("Product name cannot be empty");
        }
}
