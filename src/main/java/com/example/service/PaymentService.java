package com.example.service;

import com.example.dto.requestDto.PaymentSchemeRequestDto;
import com.example.entity.PaymentSchemeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {

    List<PaymentSchemeEntity> findAll();

    PaymentSchemeEntity findById(int schemeId);

    PaymentSchemeEntity save(PaymentSchemeRequestDto PaymentSchemeRequestDto);

}
