package com.learning.ecommerce.product.infrastructure.mapper;

import com.learning.ecommerce.product.domain.model.Product;
import com.learning.ecommerce.product.domain.model.ProductId;
import com.learning.ecommerce.product.infrastructure.persistence.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceMapper {

    public Product toDomain(ProductEntity entity) {
        return new Product(
                new ProductId(entity.getId()),
                entity.getName(),
                entity.getPrice()
        );
    }

    public ProductEntity toEntity(Product product) {
        return new ProductEntity(
                product.id().value(),
                product.name(),
                product.price()
        );
    }
}
