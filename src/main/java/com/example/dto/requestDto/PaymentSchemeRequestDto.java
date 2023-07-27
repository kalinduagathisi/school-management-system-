package com.example.dto.requestDto;

import com.example.entity.PaymentPlanEntity;
import lombok.Data;

@Data
public class PaymentSchemeRequestDto {

    private String schemeName;

    private String schemeType;

    private PaymentPlanEntity paymentPlanEntity;
}
