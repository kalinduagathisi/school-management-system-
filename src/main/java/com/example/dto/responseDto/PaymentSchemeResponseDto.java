package com.example.dto.responseDto;

import com.example.entity.PaymentPlanEntity;
import lombok.Data;

@Data
public class PaymentSchemeResponseDto {

    private Integer schemeId;

    private String schemeName;

    private String schemeType;

    private PaymentPlanEntity paymentPlanEntity;
}
