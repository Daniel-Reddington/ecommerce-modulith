package com.learning.ecommerce.product.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "Product search filter + pagination")
public record ProductSearchRequest(

        @Schema(description = "Page number", example = "0")
        @Min(0)
        Integer page,

        @Schema(description = "Page size", example = "10")
        @Min(1)
        Integer size,

        @Schema(description = "Product name filter")
        String name,

        @Schema(description = "Minimum price")
        @PositiveOrZero
        Double minPrice,

        @Schema(description = "Maximum price")
        @PositiveOrZero
        Double maxPrice
) {
}
