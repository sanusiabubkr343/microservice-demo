package com.bank.loansservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanDto {
    private String loanType;
    private Double totalLoan;
    private Double amountPaid;
    private Double outstandingAmount;
    private String servicePort;
}