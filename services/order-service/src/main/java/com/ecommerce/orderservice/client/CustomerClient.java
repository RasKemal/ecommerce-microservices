package com.ecommerce.orderservice.client;

import com.ecommerce.orderservice.exception.BusinessException;
import com.ecommerce.orderservice.model.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerClient {
    @Value("${application.config.customer-url}")
    private String customerUrl;
    private final WebClient webClient;

    public Optional<CustomerResponse> getCustomer(String customerId) {
        return Optional.ofNullable(
                webClient.get()
                        .uri(customerUrl + "/{customer-id}", customerId)
                        .retrieve()
                        .onStatus(
                                HttpStatusCode::isError,
                                clientResponse -> Mono.error(new BusinessException("Error occurred while requesting customer service"))
                        )
                        .bodyToMono(CustomerResponse.class)
                        .block()
        );
    }
}
