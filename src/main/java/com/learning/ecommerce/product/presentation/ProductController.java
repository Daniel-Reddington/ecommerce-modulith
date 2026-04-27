package com.learning.ecommerce.product.presentation;

import com.learning.ecommerce.product.application.filter.ProductFilter;
import com.learning.ecommerce.product.application.query.ProductQuery;
import com.learning.ecommerce.product.application.usecase.CreateProductUseCase;
import com.learning.ecommerce.product.application.usecase.GetAllProductsUseCase;
import com.learning.ecommerce.product.application.usecase.GetProductUseCase;
import com.learning.ecommerce.product.domain.model.Product;
import com.learning.ecommerce.product.presentation.dto.CreateProductRequest;
import com.learning.ecommerce.product.presentation.dto.ProductResponse;
import com.learning.ecommerce.product.presentation.dto.ProductSearchRequest;
import com.learning.ecommerce.product.presentation.mapper.ProductMapper;
import com.learning.ecommerce.product.presentation.mapper.ProductQueryMapper;
import com.learning.ecommerce.shared.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Products", description = "Product management APIs")
@RestController()
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductUseCase useCase;
    private final GetProductUseCase getProductUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final ProductMapper mapper;

    @Operation(summary = "Create Product")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProductResponse> create(@Valid @RequestBody CreateProductRequest request){
        Product product = useCase.execute(request.name(), request.price());
        return ApiResponse.created(mapper.toResponse(product));
    }

    @Operation(summary = "Get all products with filter")
    @GetMapping
    public ApiResponse<Page<ProductResponse>> getAll(@ModelAttribute @Valid ProductSearchRequest request){

        ProductQuery query = ProductQueryMapper.toQuery(request);

        Page<ProductResponse> products = getAllProductsUseCase
                .execute(query)
                .map(mapper::toResponse);

        return ApiResponse.ok(products);
    }

    @Operation(summary = "Get specific product by Id")
    @GetMapping("/{id}")
     public ApiResponse<ProductResponse> getById(@PathVariable UUID id){
        Product product = getProductUseCase.execute(id);
        return ApiResponse.ok(mapper.toResponse(product));
     }
}
