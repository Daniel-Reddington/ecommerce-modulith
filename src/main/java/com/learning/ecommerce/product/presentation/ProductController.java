package com.learning.ecommerce.product.presentation;

import com.learning.ecommerce.product.application.usecase.CreateProductUseCase;
import com.learning.ecommerce.product.application.usecase.GetAllProductsUseCase;
import com.learning.ecommerce.product.application.usecase.GetProductUseCase;
import com.learning.ecommerce.product.domain.model.Product;
import com.learning.ecommerce.product.presentation.dto.CreateProductRequest;
import com.learning.ecommerce.product.presentation.dto.ProductResponse;
import com.learning.ecommerce.product.presentation.mapper.ProductMapper;
import com.learning.ecommerce.shared.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductUseCase useCase;
    private final GetProductUseCase getProductUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final ProductMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProductResponse> create(@Valid @RequestBody CreateProductRequest request){
        Product product = useCase.execute(request.name(), request.price());
        return ApiResponse.created(mapper.toResponse(product));
    }

    @GetMapping
    public ApiResponse<List<ProductResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        List<ProductResponse> products = getAllProductsUseCase.execute(page, size).stream()
                .map(mapper::toResponse)
                .toList();
        return ApiResponse.ok(products);
    }

    @GetMapping("/{id}")
     public ApiResponse<ProductResponse> getById(@PathVariable UUID id){
        Product product = getProductUseCase.execute(id);
        return ApiResponse.ok(mapper.toResponse(product));
     }
}
