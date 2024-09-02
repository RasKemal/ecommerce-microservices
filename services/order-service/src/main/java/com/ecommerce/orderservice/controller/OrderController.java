package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.model.request.OrderRequest;
import com.ecommerce.orderservice.model.response.OrderResponse;
import com.ecommerce.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody @Valid OrderRequest request) {
        orderService.createOrder(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> getSpecificOrder(@PathVariable("order-id") Integer orderId) {
        return ResponseEntity.ok(orderService.getSpecificOrder(orderId));
    }
}
