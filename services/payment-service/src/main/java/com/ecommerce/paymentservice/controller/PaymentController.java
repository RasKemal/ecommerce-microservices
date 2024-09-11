package com.ecommerce.paymentservice.controller;

import com.ecommerce.paymentservice.model.request.PaymentRequest;
import com.ecommerce.paymentservice.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;


    @PostMapping
    public ResponseEntity<Void> createPayment(@RequestBody @Valid PaymentRequest request) {
        paymentService.createPayment(request);
        return ResponseEntity.accepted().build();
    }
}
