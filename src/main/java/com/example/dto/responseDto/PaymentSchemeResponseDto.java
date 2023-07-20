package com.example.dto.responseDto;

import lombok.Data;

@Data
public class PaymentSchemeResponseDto {

    private Integer schemeId;

    private String schemeName;

    private String schemeType;

    private String feeType;

    private double amount;
}
