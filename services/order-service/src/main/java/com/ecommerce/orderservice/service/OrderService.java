package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.exception.BusinessException;
import com.ecommerce.orderservice.kafka.OrderConfirmation;
import com.ecommerce.orderservice.kafka.OrderProducer;
import com.ecommerce.orderservice.mapper.OrderMapper;
import com.ecommerce.orderservice.client.CustomerClient;
import com.ecommerce.orderservice.model.Order;
import com.ecommerce.orderservice.client.ProductClient;
import com.ecommerce.orderservice.model.request.OrderLineRequest;
import com.ecommerce.orderservice.model.request.OrderRequest;
import com.ecommerce.orderservice.model.request.ProductPurchaseInModel;
import com.ecommerce.orderservice.model.request.ProductPurchaseRequest;
import com.ecommerce.orderservice.model.response.CustomerResponse;
import com.ecommerce.orderservice.model.response.OrderResponse;
import com.ecommerce.orderservice.model.response.ProductPurchaseResponse;
import com.ecommerce.orderservice.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public void createOrder(OrderRequest request) {
        CustomerResponse customer = customerClient.getCustomer(request.getCustomerId())
                .orElseThrow(() -> new BusinessException(
                        "Couldn't create order -> no customer exists with id : ",
                        request.getCustomerId()));
        ProductPurchaseResponse productPurchaseResponse = productClient.purchaseProducts(new ProductPurchaseRequest(request.getProducts()));
        Order order = orderRepository.save(orderMapper.mapOrderRequestToEntity(request));
        for(ProductPurchaseInModel purchase: request.getProducts()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            order.getId(),
                            purchase.getProductId(),
                            purchase.getQuantity()
                    )
            );
        }

        // todo:start payment process

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.getReference(),
                        request.getAmount(),
                        request.getPaymentMethod(),
                        customer,
                        productPurchaseResponse
                )
        );
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::mapOrderEntityToResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse getSpecificOrder(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::mapOrderEntityToResponse)
                .orElseThrow(() -> new EntityNotFoundException("No order with the id " + orderId));
    }
}
