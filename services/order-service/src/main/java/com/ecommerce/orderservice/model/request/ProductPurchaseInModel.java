package com.ecommerce.orderservice.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPurchaseInModel {
    @NotNull(message = "Cannot purchase without a product")
    private Integer productId;
    @Positive(message = "Quantity is mandatory")
    private double quantity;

    public ProductPurchaseInModel(Integer productId, double quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
