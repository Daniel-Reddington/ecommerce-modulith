package com.learning.ecommerce.product.application.filter;

public record ProductFilter(
        int page,
        int size,
        String name,
        Double minPrice,
        Double maxPrice
) {
}
