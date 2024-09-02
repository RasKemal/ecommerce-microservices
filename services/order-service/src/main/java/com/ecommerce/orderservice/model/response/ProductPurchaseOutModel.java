package com.ecommerce.orderservice.model.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductPurchaseOutModel {
    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;

    public ProductPurchaseOutModel(Integer productId, String name, String description, BigDecimal price, double quantity) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}
