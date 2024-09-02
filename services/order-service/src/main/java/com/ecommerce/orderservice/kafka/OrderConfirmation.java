package com.ecommerce.orderservice.kafka;

import com.ecommerce.orderservice.model.constants.PaymentMethod;
import com.ecommerce.orderservice.model.response.CustomerResponse;
import com.ecommerce.orderservice.model.response.ProductPurchaseOutModel;
import com.ecommerce.orderservice.model.response.ProductPurchaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderConfirmation {
    private String orderReference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private CustomerResponse customer;
    private ProductPurchaseResponse products;
}
