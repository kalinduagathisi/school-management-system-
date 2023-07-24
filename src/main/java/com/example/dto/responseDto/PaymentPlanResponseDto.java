package com.example.dto.responseDto;

import lombok.Data;

@Data
public class PaymentPlanResponseDto {
    private Integer paymentPlanId;
    private String feeType;
    private double amount;
}
