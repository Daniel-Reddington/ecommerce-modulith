package com.learning.ecommerce.product.presentation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductSearchRequest(
        @Min(0)
        Integer page,
        @Min(1)
        Integer size,
        String name,
        @PositiveOrZero
        Double minPrice,
        @PositiveOrZero
        Double maxPrice
) {
}
