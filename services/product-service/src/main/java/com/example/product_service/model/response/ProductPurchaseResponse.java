package com.example.product_service.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductPurchaseResponse {
    private List<ProductPurchaseOutModel> productPurchaseList;

    public ProductPurchaseResponse(List<ProductPurchaseOutModel> productPurchaseList) {
        this.productPurchaseList = productPurchaseList;
    }
}
