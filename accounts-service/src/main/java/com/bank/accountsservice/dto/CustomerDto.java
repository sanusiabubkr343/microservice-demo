package com.bank.accountsservice.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CustomerDto {
    private Long customerId;
    private String name;
    private String email;
    private String mobileNumber;
    private String servicePort;   // Used to verify which instance the request lands on
}