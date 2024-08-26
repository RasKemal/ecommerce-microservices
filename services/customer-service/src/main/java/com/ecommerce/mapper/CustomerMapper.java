package com.ecommerce.mapper;


import com.ecommerce.model.Customer;
import com.ecommerce.model.request.CreateCustomerRequest;
import com.ecommerce.model.response.CustomerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerMapper {

    public Customer mapRequestToCustomerDocument(CreateCustomerRequest request) {
        if(request == null) {
            return null;
        }

        return Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .address(request.getAddress())
                .build();
    }
    public CustomerResponse mapCustomerDocumentToResponse(Customer customer) {
        if(customer == null) {
            return null;
        }
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress());
    }
}
