package com.ecommerce.paymentservice.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class NotificationProducer {
    private final KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(NotificationProducer.class);
    public void sendNotification(PaymentNotificationRequest request) {
        logger.info("Sending notification with body: <{}>", request);
        Message<PaymentNotificationRequest> message = MessageBuilder
                .withPayload(request)
                .setHeader(KafkaHeaders.TOPIC, "payment-topic")
                .build();
        kafkaTemplate.send(message);
    }
}
