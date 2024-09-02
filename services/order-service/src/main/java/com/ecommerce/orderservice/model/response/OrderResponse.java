package com.ecommerce.orderservice.model.response;

import com.ecommerce.orderservice.model.constants.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private String reference;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String customerId;
}
