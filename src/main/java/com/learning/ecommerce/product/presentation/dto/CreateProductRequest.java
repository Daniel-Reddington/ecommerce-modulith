package com.learning.ecommerce.product.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Request to create a product")
public record CreateProductRequest(
        @Schema(description = "Product name", example = "Laptop")
        @NotBlank
        String name,

        @Schema(description = "Product price", example = "1000.0")
        @NotNull
        @Positive
        Double price) {
}
