package com.learning.ecommerce.product.infrastructure.specification;

import com.learning.ecommerce.product.infrastructure.persistence.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<ProductEntity> nameContains(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<ProductEntity> priceBetween(Double min, Double max) {

        return (root, query, criteriaBuilder) ->{
            if(min == null && max == null) return null;
            if(min != null && max != null) {
                return criteriaBuilder.between(root.get("price"), min, max);
            }
            if(min != null) return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), min);

            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), max);
        };
    }
}
