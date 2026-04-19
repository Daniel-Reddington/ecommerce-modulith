package com.learning.ecommerce.product.application.port;

import com.learning.ecommerce.product.domain.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Product save(Product product);

    Optional<Product> findById(UUID id);

    List<Product> search(int page, int size, String name, Double minPrice, Double maxPrice);

    List<Product> findAll(int page, int size);
}
