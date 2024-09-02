package com.ecommerce.orderservice.model.request;

import com.ecommerce.orderservice.model.constants.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private String reference;
    @Positive(message = "Amount must be positive.")
    private BigDecimal amount;
    @NotNull(message = "Payment method cannot be empty")
    private PaymentMethod paymentMethod;
    @NotNull(message = "Customer id cannot be empty")
    private String customerId;
    @NotEmpty(message = "At least one product should be purchased")
    private List<ProductPurchaseInModel> products;

    public OrderRequest(String reference, BigDecimal amount, PaymentMethod paymentMethod, String customerId, List<ProductPurchaseInModel> products) {
        this.reference = reference;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.customerId = customerId;
        this.products = products;
    }
}
