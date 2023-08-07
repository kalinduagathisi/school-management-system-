package com.example.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PaymentSchemeRequestDto {

    @NotNull
    List<Map<String, Object>> paymentPlanEntityList;
    @NotNull
    @NotBlank(message = "Scheme name cannot be empty..")
    private String schemeName;
    @NotNull
    @NotBlank(message = "Scheme type cannot be empty..")
    private String schemeType;

}
