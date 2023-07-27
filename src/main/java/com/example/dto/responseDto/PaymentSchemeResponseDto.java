package com.example.dto.responseDto;

import com.example.entity.PaymentPlanEntity;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PaymentSchemeResponseDto {

    private Integer schemeId;

    private String schemeName;

    private String schemeType;

    List<Map<String, Object>> paymentPlanEntityList;

//    private List<PaymentPlanEntity> paymentPlanEntity;


}
