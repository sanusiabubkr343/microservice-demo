
package com.bank.loansservice.mapper;

import com.bank.loansservice.dto.LoanDto;
import com.bank.loansservice.entity.Loan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {
    @Value("${server.port}")
    private String port;

    public LoanDto toDto(Loan loan) {
        return LoanDto.builder()
                .loanType(loan.getLoanType())
                .totalLoan(loan.getTotalLoan())
                .amountPaid(loan.getAmountPaid())
                .outstandingAmount(loan.getOutstandingAmount())
                .servicePort(port)
                .build();
    }
}