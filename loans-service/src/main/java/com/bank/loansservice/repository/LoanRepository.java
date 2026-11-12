package com.bank.loansservice.repository;

import com.bank.loansservice.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByMobileNumber(String mobileNumber);
}