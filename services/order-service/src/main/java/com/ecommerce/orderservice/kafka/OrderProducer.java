package com.ecommerce.orderservice.kafka;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final Logger logger = LoggerFactory.getLogger(OrderProducer.class);
    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;
    public void sendOrderConfirmation(OrderConfirmation orderConfirmation) {
        logger.info("Sending order confirmation...");
        Message<OrderConfirmation> message = MessageBuilder
                .withPayload(orderConfirmation)
                .setHeader(KafkaHeaders.TOPIC, "order-topic")
                .build();
        kafkaTemplate.send(message);
    }
}
