package com.example.dto.responseDto;

import com.example.entity.PaymentSchemeEntity;

import com.example.enums.StudentStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentResponseDto {

    private Integer studentId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private PaymentSchemeEntity paymentSchemeEntity;
    private StudentStatus studentStatus;
}
