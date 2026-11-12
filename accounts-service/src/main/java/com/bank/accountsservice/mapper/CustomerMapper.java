package com.bank.accountsservice.mapper;

import com.bank.accountsservice.dto.CreateCustomerRequest;

import com.bank.accountsservice.dto.CustomerDto;
import com.bank.accountsservice.entity.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    @Value("${server.port}")
    private String port;

    public CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .email(customer.getEmail())
                .mobileNumber(customer.getMobileNumber())
                .servicePort(port)
                .build();
    }

    public Customer toEntity(CreateCustomerRequest request) {
        return Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .mobileNumber(request.getMobileNumber())
                .build();
    }
}