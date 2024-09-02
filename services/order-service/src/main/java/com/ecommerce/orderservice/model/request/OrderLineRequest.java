package com.ecommerce.orderservice.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderLineRequest {
    private Integer orderId;
    private Integer productId;
    private double quantity;

    public OrderLineRequest(Integer orderId, Integer productId, double quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
