package com.example.dto.requestDto;

import com.example.user.PaymentPlan;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PaymentSchemeRequestDto {

    private String schemeName;

    private String schemeType;

    private PaymentPlan paymentPlan;
}
