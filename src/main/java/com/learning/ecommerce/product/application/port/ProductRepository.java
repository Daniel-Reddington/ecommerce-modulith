package com.learning.ecommerce.product.application.port;

import com.learning.ecommerce.product.application.filter.ProductFilter;
import com.learning.ecommerce.product.domain.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Product save(Product product);

    Optional<Product> findById(UUID id);

    Page<Product> findAll(ProductFilter filter);

}
