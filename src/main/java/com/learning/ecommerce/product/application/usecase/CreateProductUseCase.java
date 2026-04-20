package com.learning.ecommerce.product.application.usecase;

import com.learning.ecommerce.product.application.port.ProductRepository;
import com.learning.ecommerce.product.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductUseCase {

    private final ProductRepository repository;

    public Product execute(String name, double price) {
        Product product = Product.create(name, price);
        return repository.save(product);
    }
}
