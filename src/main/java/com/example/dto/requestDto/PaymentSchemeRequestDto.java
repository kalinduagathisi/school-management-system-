package com.example.dto.requestDto;

import lombok.Data;

@Data
public class PaymentSchemeRequestDto {

    private String schemeName;

    private String schemeType;

    private String feeType;

    private double amount;
}
