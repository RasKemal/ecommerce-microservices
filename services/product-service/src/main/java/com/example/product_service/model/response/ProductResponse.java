package com.example.product_service.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponse {
    private Integer id;
    private String name;
    private String description;
    private double quantity;
    private BigDecimal price;
    private Integer categoryId;
    private String categoryName;
    private String categoryDescription;

    public ProductResponse(Integer id, String name, String description, double quantity, BigDecimal price, Integer categoryId, String categoryName, String categoryDescription) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }
}
