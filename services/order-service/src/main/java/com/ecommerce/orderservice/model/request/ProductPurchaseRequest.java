package com.ecommerce.orderservice.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductPurchaseRequest {
    private List<ProductPurchaseInModel> productPurchaseList;
    public ProductPurchaseRequest(List<ProductPurchaseInModel> productPurchaseList) {
        this.productPurchaseList = productPurchaseList;
    }
}
