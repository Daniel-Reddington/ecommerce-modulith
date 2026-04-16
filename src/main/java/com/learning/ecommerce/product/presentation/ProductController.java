package com.learning.ecommerce.product.presentation;

import com.learning.ecommerce.product.application.usecase.CreateProductUseCase;
import com.learning.ecommerce.product.domain.model.Product;
import com.learning.ecommerce.product.presentation.dto.CreateProductRequest;
import com.learning.ecommerce.product.presentation.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductUseCase useCase;

    @PostMapping
    public ProductResponse create(@RequestBody CreateProductRequest request){
        Product product = useCase.execute(request.name(), request.price());
        return new ProductResponse(product.id().value(), product.name(), product.price());
    }
}
