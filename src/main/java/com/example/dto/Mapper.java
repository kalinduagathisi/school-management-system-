package com.example.dto;

import com.example.dto.responseDto.PaymentPlanResponseDto;
import com.example.dto.responseDto.PaymentSchemeResponseDto;
import com.example.dto.responseDto.StudentResponseDto;
import com.example.entity.PaymentPlanEntity;
import com.example.entity.PaymentSchemeEntity;
import com.example.entity.StudentEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {

    // Map a single StudentEntity to a StudentResponseDto
    public static StudentResponseDto studentToStudentResponseDto(StudentEntity studentEntity) {
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setStudentId(studentEntity.getStudentId());
        studentResponseDto.setFirstName(studentEntity.getFirstName());
        studentResponseDto.setLastName(studentEntity.getLastName());
        studentResponseDto.setEmail(studentEntity.getEmail());
        studentResponseDto.setDateOfBirth(studentEntity.getDateOfBirth());
        studentResponseDto.setPaymentSchemeEntity(studentEntity.getPaymentSchemeEntity());

        return studentResponseDto;
    }

    // Map a list of StudentEntities to a list of StudentResponseDtos
    public static List<StudentResponseDto> studentsToStudentsResponseDto(List<StudentEntity> studentEntities) {
        List<StudentResponseDto> studentResponseDtos = new ArrayList<>();
        for (StudentEntity studentEntity : studentEntities) {
            studentResponseDtos.add(studentToStudentResponseDto(studentEntity));
        }
        return studentResponseDtos;
    }

    // Map a single PaymentSchemeEntity to a PaymentSchemeResponseDto
    public static PaymentSchemeResponseDto paymentSchemeToPaymentSchemeDto(PaymentSchemeEntity paymentSchemeEntity) {
        PaymentSchemeResponseDto paymentSchemeResponseDto = new PaymentSchemeResponseDto();

        paymentSchemeResponseDto.setSchemeId(paymentSchemeEntity.getSchemeId());
        paymentSchemeResponseDto.setSchemeName(paymentSchemeEntity.getSchemeName());
        paymentSchemeResponseDto.setSchemeType(paymentSchemeEntity.getSchemeType());
//        paymentSchemeResponseDto.setPaymentPlanEntityList(paymentSchemeEntity.getPaymentPlanEntity());

        List<PaymentPlanEntity> paymentPlanEntities = paymentSchemeEntity.getPaymentPlanEntity();
        if (paymentPlanEntities != null) {
            List<Map<String, Object>> paymentPlanMaps = toListOfMaps(paymentPlanEntities);
            paymentSchemeResponseDto.setPaymentPlanEntityList(paymentPlanMaps);
        }

        return paymentSchemeResponseDto;
    }

    // Convert a list of PaymentPlanEntity to a list of Map<String, Object>
    public static List<Map<String, Object>> toListOfMaps(List<PaymentPlanEntity> paymentPlanEntities) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (PaymentPlanEntity paymentPlanEntity : paymentPlanEntities) {
            Map<String, Object> paymentPlanMap = new HashMap<>();
            paymentPlanMap.put("feeType", paymentPlanEntity.getFeeType());
            paymentPlanMap.put("amount", paymentPlanEntity.getAmount());
            result.add(paymentPlanMap);
        }
        return result;
    }

    // Map a list of PaymentSchemeEntities to a list of PaymentSchemeResponseDtos
    public static List<PaymentSchemeResponseDto> paymentSchemesToPaymentSchemeDtos(List<PaymentSchemeEntity> paymentSchemeEntities) {
        List<PaymentSchemeResponseDto> paymentSchemeResponseDtos = new ArrayList<>();
        for (PaymentSchemeEntity paymentSchemeEntity : paymentSchemeEntities) {
            paymentSchemeResponseDtos.add(paymentSchemeToPaymentSchemeDto(paymentSchemeEntity));
        }
        return paymentSchemeResponseDtos;
    }

    // Map a single PaymentPlanEntity to a PaymentPlanResponseDto
    public static PaymentPlanResponseDto paymentPlanToPaymentPlanResponseDto(PaymentPlanEntity paymentPlanEntity) {
        PaymentPlanResponseDto paymentPlanResponseDto = new PaymentPlanResponseDto();
        paymentPlanResponseDto.setPaymentPlanId(paymentPlanEntity.getPaymentPlanId());
        paymentPlanResponseDto.setAmount(paymentPlanEntity.getAmount());
        return paymentPlanResponseDto;
    }
}
