package com.example.dto;

import com.example.dto.responseDto.PaymentPlanResponseDto;
import com.example.dto.responseDto.PaymentSchemeResponseDto;
import com.example.dto.responseDto.StudentResponseDto;
import com.example.entity.PaymentPlanEntity;
import com.example.entity.PaymentSchemeEntity;
import com.example.entity.StudentEntity;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    // one studentEntity
    public static StudentResponseDto studentToStudentResponseDto(StudentEntity studentEntity){
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setStudentId(studentEntity.getStudentId());
        studentResponseDto.setFirstName(studentEntity.getFirstName());
        studentResponseDto.setLastName(studentEntity.getLastName());
        studentResponseDto.setEmail(studentEntity.getEmail());
        studentResponseDto.setDateOfBirth(studentEntity.getDateOfBirth());
        studentResponseDto.setPaymentSchemeEntity(studentEntity.getPaymentSchemeEntity());

        return studentResponseDto;
    }

    // list of studentEntities
    public static List<StudentResponseDto> studentsToStudentsResponseDto(List<StudentEntity> studentEntities){
        List<StudentResponseDto> studentResponseDtos = new ArrayList<>();
        for (StudentEntity studentEntity : studentEntities){
            studentResponseDtos.add(studentToStudentResponseDto(studentEntity));
        }
        return studentResponseDtos;
    }

    // one payment scheme
    public static PaymentSchemeResponseDto paymentSchemeToPaymentSchemeDto(PaymentSchemeEntity paymentSchemeEntity){
        PaymentSchemeResponseDto paymentSchemeResponseDto = new PaymentSchemeResponseDto();
        paymentSchemeResponseDto.setSchemeId(paymentSchemeEntity.getSchemeId());
        paymentSchemeResponseDto.setSchemeName(paymentSchemeEntity.getSchemeName());
        paymentSchemeResponseDto.setSchemeType(paymentSchemeEntity.getSchemeType());
        paymentSchemeResponseDto.setPaymentPlanEntity(paymentSchemeEntity.getPaymentPlanEntity());
        return paymentSchemeResponseDto;
    }

    // many payment schemes
    public static List<PaymentSchemeResponseDto> paymentSchemesToPaymentSchemeDtos(List<PaymentSchemeEntity> paymentSchemeEntities){
        List<PaymentSchemeResponseDto> paymentSchemeResponseDtos = new ArrayList<>();
        for (PaymentSchemeEntity paymentSchemeEntity : paymentSchemeEntities){
            paymentSchemeResponseDtos.add(paymentSchemeToPaymentSchemeDto(paymentSchemeEntity));
        }
        return paymentSchemeResponseDtos;
    }

    // payment plan
    public static PaymentPlanResponseDto paymentPlanToPaymentPlanResponseDto(PaymentPlanEntity paymentPlanEntity){
        PaymentPlanResponseDto paymentPlanResponseDto = new PaymentPlanResponseDto();
        paymentPlanResponseDto.setPaymentPlanId(paymentPlanEntity.getPaymentPlanId());
        paymentPlanResponseDto.setAmount(paymentPlanEntity.getAmount());
        return paymentPlanResponseDto;
    }
}
