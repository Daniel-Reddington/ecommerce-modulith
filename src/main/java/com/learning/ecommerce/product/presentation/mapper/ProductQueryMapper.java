package com.learning.ecommerce.product.presentation.mapper;

import com.learning.ecommerce.product.application.filter.ProductFilter;
import com.learning.ecommerce.product.application.query.ProductQuery;
import com.learning.ecommerce.product.presentation.dto.ProductSearchRequest;

public class ProductQueryMapper {
    public static ProductQuery toQuery(ProductSearchRequest request) {
        int page = request.page() != null ? request.page() : 0;
        int size = request.size() != null ? request.size() : 10;

        ProductFilter filter = new ProductFilter(page, size, request.name(), request.minPrice(),  request.maxPrice());

        return new ProductQuery(filter);
    }
}
