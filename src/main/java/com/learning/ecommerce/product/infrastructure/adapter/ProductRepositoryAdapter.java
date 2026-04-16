package com.learning.ecommerce.product.infrastructure.adapter;

import com.learning.ecommerce.product.application.port.ProductRepository;
import com.learning.ecommerce.product.domain.model.Product;
import com.learning.ecommerce.product.domain.model.ProductId;
import com.learning.ecommerce.product.infrastructure.persistence.ProductEntity;
import com.learning.ecommerce.product.infrastructure.persistence.SpringDataProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {

    private final SpringDataProductRepository jpaRepository;

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = new ProductEntity(
                product.id().value(),
                product.name(),
                product.price()
        );

        ProductEntity saved = jpaRepository.save(productEntity);

        return new Product(
                new ProductId(saved.getId()),
                saved.getName(),
                saved.getPrice()
        );
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

}
