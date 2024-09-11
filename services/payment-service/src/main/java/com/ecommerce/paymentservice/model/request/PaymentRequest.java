package com.ecommerce.paymentservice.model.request;

import com.ecommerce.paymentservice.model.Customer;
import com.ecommerce.paymentservice.model.constants.PaymentMethod;
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
    private Customer customer;
}
