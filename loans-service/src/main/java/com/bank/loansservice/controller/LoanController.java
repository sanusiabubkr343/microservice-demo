package com.bank.loansservice.controller;

import com.bank.loansservice.dto.LoanDto;
import com.bank.loansservice.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService service;

    @GetMapping("/{mobileNumber}")
    public ResponseEntity<List<LoanDto>> getLoans(@PathVariable String mobileNumber) {
        return ResponseEntity.ok(service.findByMobile(mobileNumber));
    }
}