package com.learning.ecommerce.product.presentation.mapper;

import com.learning.ecommerce.product.domain.model.Product;
import com.learning.ecommerce.product.presentation.dto.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(product.id().value(), product.name(), product.price());
    }
}
