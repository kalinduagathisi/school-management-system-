package com.example.dto;

import com.example.dto.responseDto.PaymentSchemeResponseDto;
import com.example.dto.responseDto.StudentResponseDto;
import com.example.user.PaymentScheme;
import com.example.user.Student;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    // one student
    public static StudentResponseDto studentToStudentResponseDto(Student student){
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setStudentId(student.getStudentId());
        studentResponseDto.setFirstName(student.getFirstName());
        studentResponseDto.setLastName(student.getLastName());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setDateOfBirth(student.getDateOfBirth());
        studentResponseDto.setPaymentScheme(student.getPaymentScheme());

        return studentResponseDto;
    }

    // list of students
    public static List<StudentResponseDto> studentsToStudentsResponseDto(List<Student> students){
        List<StudentResponseDto> studentResponseDtos = new ArrayList<>();
        for (Student student: students){
            studentResponseDtos.add(studentToStudentResponseDto(student));
        }
        return studentResponseDtos;
    }

    // one payment scheme
    public static PaymentSchemeResponseDto paymentSchemeToPaymentSchemeDto(PaymentScheme paymentScheme){
        PaymentSchemeResponseDto paymentSchemeResponseDto = new PaymentSchemeResponseDto();
        paymentSchemeResponseDto.setSchemeId(paymentScheme.getSchemeId());
        paymentSchemeResponseDto.setSchemeName(paymentScheme.getSchemeName());
        paymentSchemeResponseDto.setSchemeType(paymentScheme.getSchemeType());
        paymentSchemeResponseDto.setFeeType(paymentScheme.getFeeType());
        paymentSchemeResponseDto.setAmount(paymentScheme.getAmount());

        return paymentSchemeResponseDto;
    }

    // many payment schemes
    public static List<PaymentSchemeResponseDto> paymentSchemesToPaymentSchemeDtos(List<PaymentScheme> paymentSchemes){
        List<PaymentSchemeResponseDto> paymentSchemeResponseDtos = new ArrayList<>();
        for (PaymentScheme paymentScheme: paymentSchemes){
            paymentSchemeResponseDtos.add(paymentSchemeToPaymentSchemeDto(paymentScheme));
        }
        return paymentSchemeResponseDtos;
    }
}
