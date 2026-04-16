package com.learning.ecommerce.product.application.usecase;

import com.learning.ecommerce.product.application.port.ProductRepository;
import com.learning.ecommerce.product.domain.model.Product;
import com.learning.ecommerce.product.presentation.dto.ProductResponse;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class GetProductUseCase {

    private final ProductRepository productRepository;

        public Product execute(UUID id) {
            return productRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
        }

}
