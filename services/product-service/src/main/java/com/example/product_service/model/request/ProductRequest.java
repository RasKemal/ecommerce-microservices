package com.example.product_service.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequest {
    private String name;
    @NotNull(message = "Product description cannot be empty.")
    private String description;
    @Positive(message = "Quantity should be positive.")
    private double quantity;
    @Positive(message = "Price should be positive.")
    private BigDecimal price;
    @NotNull(message = "Category id cannot be empty.")
    private Integer categoryId;

    public ProductRequest(String name, String description, double quantity, BigDecimal price, Integer categoryId) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.categoryId = categoryId;
    }
}
