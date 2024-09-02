package com.ecommerce.orderservice.service;


import com.ecommerce.orderservice.mapper.OrderMapper;
import com.ecommerce.orderservice.model.Order;
import com.ecommerce.orderservice.model.OrderLine;
import com.ecommerce.orderservice.model.request.OrderLineRequest;
import com.ecommerce.orderservice.model.response.OrderLineResponse;
import com.ecommerce.orderservice.repository.OrderLineRepository;
import com.ecommerce.orderservice.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineRepository orderLineRepository;

    public void saveOrderLine(OrderLineRequest orderLineRequest) {
        Order order = this.orderRepository.findById(orderLineRequest.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        OrderLine orderLine = this.orderMapper.mapOrderLineRequestToEntity(orderLineRequest);
        orderLine.setOrder(order);
        orderLineRepository.save(orderLine);
    }

    public List<OrderLineResponse> findOrderLinesByOrder(Integer orderId) {
        return orderLineRepository.findOrderLinesByOrderId(orderId)
                .stream()
                .map(orderMapper::mapOrderLineEntityToResponse)
                .collect(Collectors.toList());
    }
}
