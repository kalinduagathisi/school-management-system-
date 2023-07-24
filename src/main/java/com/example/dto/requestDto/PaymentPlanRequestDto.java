package com.example.dto.requestDto;

import lombok.Data;

@Data
public class PaymentPlanRequestDto {
    private String feeType;
    private double amount;
}
