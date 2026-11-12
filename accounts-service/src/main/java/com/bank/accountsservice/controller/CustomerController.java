package com.bank.accountsservice.controller;


import com.bank.accountsservice.dto.CreateCustomerRequest;
import com.bank.accountsservice.dto.CustomerDto;
import com.bank.accountsservice.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> create(@Valid @RequestBody CreateCustomerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/customers/{mobileNumber}")
    public ResponseEntity<CustomerDto> getByMobile(@PathVariable String mobileNumber) {
        return ResponseEntity.ok(service.findByMobile(mobileNumber));
    }
}