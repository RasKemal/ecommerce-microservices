package com.ecommerce.orderservice.client;

import com.ecommerce.orderservice.exception.BusinessException;
import com.ecommerce.orderservice.model.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PaymentClient {
    @Value("${application.config.payment-url}")
    private String paymentUrl;
    private final WebClient webClient;

    public void requestOrderPayment(PaymentRequest requestBody) {
        webClient.post()
                .uri(paymentUrl)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        clientResponse -> Mono.error(new BusinessException("Error occurred while requesting payment service"))
                )
                .bodyToMono(Void.class)
                .block();
    }
}
