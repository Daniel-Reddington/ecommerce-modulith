package com.learning.ecommerce.product.presentation.dto;

import java.util.UUID;

public record ProductResponse(UUID id, String name, Double price) {
}
