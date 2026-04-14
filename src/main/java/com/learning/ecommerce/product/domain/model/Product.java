package com.learning.ecommerce.product.domain.model;

public class Product {

    private final ProductId id;
    private final String name;
    private final double price;


    public Product(ProductId id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static Product create(String name, double price) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot empty");
        }

        if(price <= 0){
            throw new IllegalArgumentException("price must be > 0");
        }

        return new Product(ProductId.generate(), name, price);
    }

    public ProductId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public double price() {
        return price;
    }


}
