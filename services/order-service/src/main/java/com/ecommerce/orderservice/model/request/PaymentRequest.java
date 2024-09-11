package com.ecommerce.orderservice.model.request;

import com.ecommerce.orderservice.model.constants.PaymentMethod;
import com.ecommerce.orderservice.model.response.CustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private Integer orderId;
    private String orderReference;
    private CustomerResponse customer;
}
