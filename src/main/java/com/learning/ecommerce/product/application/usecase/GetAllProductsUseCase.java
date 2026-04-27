package com.learning.ecommerce.product.application.usecase;

import com.learning.ecommerce.product.application.filter.ProductFilter;
import com.learning.ecommerce.product.application.port.ProductRepository;
import com.learning.ecommerce.product.application.query.ProductQuery;
import com.learning.ecommerce.product.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllProductsUseCase {

    private final ProductRepository productRepository;

    public Page<Product> execute(ProductQuery query) {
        return productRepository.findAll(query.filter());
    }

}
