package com.learning.ecommerce.product.infrastructure.adapter;

import com.learning.ecommerce.product.application.filter.ProductFilter;
import com.learning.ecommerce.product.application.port.ProductRepository;
import com.learning.ecommerce.product.domain.model.Product;
import com.learning.ecommerce.product.domain.model.ProductId;
import com.learning.ecommerce.product.infrastructure.mapper.ProductPersistenceMapper;
import com.learning.ecommerce.product.infrastructure.persistence.ProductEntity;
import com.learning.ecommerce.product.infrastructure.persistence.SpringDataProductRepository;
import com.learning.ecommerce.product.infrastructure.specification.ProductSpecification;
import com.learning.ecommerce.product.presentation.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static javax.management.Query.and;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {

    private final SpringDataProductRepository jpaRepository;
    private final ProductPersistenceMapper mapper;

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = new ProductEntity(
                product.id().value(),
                product.name(),
                product.price()
        );

        ProductEntity saved = jpaRepository.save(productEntity);

        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }



    @Override
    public Page<Product> findAll(ProductFilter filter) {
        Pageable pageable = PageRequest.of(filter.page(), filter.size());

        Specification<ProductEntity> spec = Specification
                .where(ProductSpecification.nameContains(filter.name()))
                .and(ProductSpecification.priceBetween(filter.minPrice(), filter.maxPrice()));

        Page<ProductEntity> results = jpaRepository.findAll(spec, pageable);

        return results.map(mapper::toDomain);
    }

}
