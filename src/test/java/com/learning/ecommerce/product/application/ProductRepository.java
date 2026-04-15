package com.learning.ecommerce.product.application;

import com.learning.ecommerce.product.domain.model.Product;

public interface ProductRepository {
    Product save(Product product);
}
