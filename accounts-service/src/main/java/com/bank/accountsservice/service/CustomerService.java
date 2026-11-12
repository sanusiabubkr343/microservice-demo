package com.bank.accountsservice.service;

import com.bank.accountsservice.dto.CreateCustomerRequest;
import com.bank.accountsservice.dto.CustomerDto;
import com.bank.accountsservice.entity.Customer;
import com.bank.accountsservice.exception.CustomerNotFoundException;
import com.bank.accountsservice.mapper.CustomerMapper;
import com.bank.accountsservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public CustomerDto create(CreateCustomerRequest request) {
        Customer saved = repository.save(mapper.toEntity(request));
        return mapper.toDto(saved);
    }

    public CustomerDto findByMobile(String mobileNumber) {
        return repository.findByMobileNumber(mobileNumber)
                .map(mapper::toDto)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + mobileNumber));
    }
}