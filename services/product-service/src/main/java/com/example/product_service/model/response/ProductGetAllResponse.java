package com.example.product_service.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductGetAllResponse {
    private List<ProductResponse> products;

    public ProductGetAllResponse(List<ProductResponse> products) {
        this.products = products;
    }
}
