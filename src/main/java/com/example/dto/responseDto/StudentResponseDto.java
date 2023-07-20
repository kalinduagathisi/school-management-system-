package com.example.dto.responseDto;

import com.example.user.PaymentScheme;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentResponseDto {

    private Integer studentId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private PaymentScheme paymentScheme;
}
