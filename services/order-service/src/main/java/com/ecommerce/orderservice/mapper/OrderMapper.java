package com.ecommerce.orderservice.mapper;

import com.ecommerce.orderservice.model.Order;
import com.ecommerce.orderservice.model.OrderLine;
import com.ecommerce.orderservice.model.request.OrderLineRequest;
import com.ecommerce.orderservice.model.request.OrderRequest;
import com.ecommerce.orderservice.model.response.OrderLineResponse;
import com.ecommerce.orderservice.model.response.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {


    public Order mapOrderRequestToEntity(OrderRequest orderRequest) {
        return Order.builder()
                .costumerId(orderRequest.getCustomerId())
                .reference(orderRequest.getReference())
                .amount(orderRequest.getAmount())
                .paymentMethod(orderRequest.getPaymentMethod())
                .build();
    }

    public OrderLine mapOrderLineRequestToEntity(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .quantity(orderLineRequest.getQuantity())
                .productId(orderLineRequest.getProductId())
                .build();
    }

    public OrderResponse mapOrderEntityToResponse(Order order) {
        return new OrderResponse(
                order.getReference(),
                order.getAmount(),
                order.getPaymentMethod(),
                order.getCostumerId()
        );
    }

    public OrderLineResponse mapOrderLineEntityToResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }

}
