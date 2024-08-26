package com.ecommerce.model.request;


import com.ecommerce.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateCustomerRequest {
    private String id;
    @NotNull(message = "Customer name cannot be empty")
    private String firstName;
    @NotNull(message = "Customer last name cannot be empty")
    private String lastName;
    @NotNull(message = "Customer last name cannot be empty")
    @Email(message = "Customer email is not valid")
    private String email;
    private Address address;
}
