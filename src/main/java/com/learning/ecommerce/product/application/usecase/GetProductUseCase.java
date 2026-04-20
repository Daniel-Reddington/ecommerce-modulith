package com.learning.ecommerce.product.application.usecase;

import com.learning.ecommerce.product.application.port.ProductRepository;
import com.learning.ecommerce.product.domain.exception.ProductNotFoundException;
import com.learning.ecommerce.product.domain.model.Product;
import com.learning.ecommerce.product.presentation.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetProductUseCase {

    private final ProductRepository productRepository;

        public Product execute(UUID id) {
            return productRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException(id));
        }

}
