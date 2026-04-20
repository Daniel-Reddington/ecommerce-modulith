package com.learning.ecommerce.product.application.usecase;

import com.learning.ecommerce.product.application.port.ProductRepository;
import com.learning.ecommerce.product.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllProductsUseCase {

    private final ProductRepository productRepository;

    public List<Product> execute(int page, int size) {
        return productRepository.findAll(page, size);
    }

    public List<Product> execute(int page, int size, String name, Double minPrice, Double maxPrice) {
        return productRepository.search(page, size, name, minPrice, maxPrice);
    }

}
