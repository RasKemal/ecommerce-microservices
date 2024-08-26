package com.ecommerce.service;


import com.ecommerce.exception.CustomerNotFoundException;
import com.ecommerce.mapper.CustomerMapper;
import com.ecommerce.model.Customer;
import com.ecommerce.model.request.CreateCustomerRequest;
import com.ecommerce.model.response.CustomerResponse;
import com.ecommerce.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper customerMapper;
    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public String createCustomer(CreateCustomerRequest request) {
        Customer customer = repository.save(customerMapper.mapRequestToCustomerDocument(request));
        logger.info("Successfully created customer with id: {}", customer.getId());
        return customer.getFirstName();
    }

    public void updateCustomer(CreateCustomerRequest request) {
        repository.save(getUpdatedCustomer(request));
        logger.info("Successfully updated customer with id: {}", request.getId());
    }

    public List<CustomerResponse> getAllCustomers() {
        List<CustomerResponse> customers =  repository.findAll()
                .stream()
                .map(customerMapper::mapCustomerDocumentToResponse)
                .collect(Collectors.toList());

        logger.info("Successfully retrieved {} customers.", customers.size());
        return customers;
    }

    public Boolean isCustomerPresent(String customerId) {
        return repository.findById(customerId)
                .isPresent();
    }

    public CustomerResponse getCustomer(String customerId) {
        return repository.findById(customerId)
                .map(customerMapper::mapCustomerDocumentToResponse)
                .orElseThrow(() ->
                        new CustomerNotFoundException(String.format("No customers found with id: %s", customerId))
                );
    }

    private Customer getUpdatedCustomer(CreateCustomerRequest request) {

        repository.findById(request.getId()).orElseThrow(() -> new CustomerNotFoundException(
                String.format("Unable to update customer: No customer found with the provided id:: %s", request.getId())
        ));
        Customer customer = repository.findById(request.getId()).orElseThrow(() -> new CustomerNotFoundException(
                String.format("Unable to update customer: No customer found with the provided id:: %s", request.getId())
        ));
        if(StringUtils.isNotBlank(request.getFirstName())) {
            customer.setFirstName(request.getFirstName());
        }

        if(StringUtils.isNotBlank(request.getLastName())) {
            customer.setFirstName(request.getLastName());
        }

        if(StringUtils.isNotBlank(request.getEmail())) {
            customer.setFirstName(request.getEmail());
        }

        if(StringUtils.isNotBlank(request.getFirstName())) {
            customer.setFirstName(request.getFirstName());
        }

        if(request.getAddress() != null) {
            customer.setAddress(request.getAddress());
        }
        return customer;
    }

    public void deleteCustomer(String customerId) {

    }
}
