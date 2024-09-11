package com.ecommerce.paymentservice.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Customer {
    private String id;
    @NotNull(message = "first name cannot be empty")
    private String firstName;
    @NotNull(message = "last name cannot be empty")
    private String lastName;
    @NotNull(message = "email cannot be empty")
    @Email(message = "email not valid")
    private String email;
}
