package com.bank.loansservice.service;

import com.bank.loansservice.dto.LoanDto;
import com.bank.loansservice.exception.LoanNotFoundException;
import com.bank.loansservice.mapper.LoanMapper;
import com.bank.loansservice.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository repository;
    private final LoanMapper mapper;

    public List<LoanDto> findByMobile(String mobileNumber) {
        List<LoanDto> loans = repository.findByMobileNumber(mobileNumber).stream()
                .map(mapper::toDto)
                .toList();
        if (loans.isEmpty()) {
            throw new LoanNotFoundException("No loans found for mobile number: " + mobileNumber);
        }
        return loans;
    }
}