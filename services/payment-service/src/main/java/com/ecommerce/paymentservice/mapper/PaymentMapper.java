package com.ecommerce.paymentservice.mapper;

import com.ecommerce.paymentservice.model.Payment;
import com.ecommerce.paymentservice.model.request.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment mapPaymentRequestToEntity(PaymentRequest request) {
        return Payment.builder()
                .paymentMethod(request.getPaymentMethod())
                .amount(request.getAmount())
                .orderId(request.getOrderId())
                .build();
    }
}
