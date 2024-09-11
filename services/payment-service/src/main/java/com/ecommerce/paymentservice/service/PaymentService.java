package com.ecommerce.paymentservice.service;


import com.ecommerce.paymentservice.mapper.PaymentMapper;
import com.ecommerce.paymentservice.model.Payment;
import com.ecommerce.paymentservice.model.request.PaymentRequest;
import com.ecommerce.paymentservice.notification.NotificationProducer;
import com.ecommerce.paymentservice.notification.PaymentNotificationRequest;
import com.ecommerce.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private NotificationProducer notificationProducer;
    public void createPayment(PaymentRequest request) {
        paymentRepository.save(paymentMapper.mapPaymentRequestToEntity(request));
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.getOrderReference(),
                        request.getAmount(),
                        request.getPaymentMethod(),
                        request.getCustomer().getFirstName(),
                        request.getCustomer().getLastName(),
                        request.getCustomer().getEmail()
                )
        );
    }
}
