package com.example.dto.requestDto;

import com.example.entity.PaymentPlanEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class PaymentSchemeRequestDto {

    private String schemeName;

    private String schemeType;

    List<Map<String, Object>> paymentPlanEntityList;

}
