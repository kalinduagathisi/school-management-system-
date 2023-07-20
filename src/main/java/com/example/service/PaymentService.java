package com.example.service;

import com.example.dto.requestDto.PaymentSchemeRequestDto;
import com.example.user.PaymentScheme;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {

    List<PaymentScheme> findAll();

    PaymentScheme findById(int schemeId);

    PaymentScheme save(PaymentSchemeRequestDto PaymentSchemeRequestDto);

}
