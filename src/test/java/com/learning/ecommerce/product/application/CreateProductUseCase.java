package com.learning.ecommerce.product.application;

import com.learning.ecommerce.product.domain.model.Product;

public class CreateProductUseCase {
    private final ProductRepository repository;

    public CreateProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    public Product execute(String name, double price) {
        Product product = Product.create(name, price);
        return repository.save(product);
    }
}
