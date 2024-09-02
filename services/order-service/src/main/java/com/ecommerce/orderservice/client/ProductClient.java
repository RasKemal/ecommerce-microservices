package com.ecommerce.orderservice.client;

import com.ecommerce.orderservice.exception.BusinessException;
import com.ecommerce.orderservice.model.request.ProductPurchaseInModel;
import com.ecommerce.orderservice.model.request.ProductPurchaseRequest;
import com.ecommerce.orderservice.model.response.ProductPurchaseOutModel;
import com.ecommerce.orderservice.model.response.ProductPurchaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {
    @Value("${application.config.product-url}")
    private String productUrl;
    private final WebClient webClient;

    public ProductPurchaseResponse purchaseProducts(ProductPurchaseRequest requestBody) {
        return webClient.post()
                .uri(productUrl + "/purchase")
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        clientResponse -> Mono.error(new BusinessException("Error occurred while requesting purchase product service"))
                )
                .bodyToMono(ProductPurchaseResponse.class)
                .block();  // Blocking here to maintain synchronous behavior
    }
}
