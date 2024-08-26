package com.example.product_service.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductPurchaseRequest {
    private List<ProductPurchaseInModel> productPurchaseList;

    public ProductPurchaseRequest() {}
    public ProductPurchaseRequest(List<ProductPurchaseInModel> productPurchaseList) {
        this.productPurchaseList = productPurchaseList;
    }
}
