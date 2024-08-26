package com.example.product_service.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPurchaseInModel {
    @NotNull(message = "Product id cannot be empty")
    private Integer productId;
    @NotNull(message = "Quantity cannot be empty")
    double quantity;

    public ProductPurchaseInModel(Integer productId, double quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
