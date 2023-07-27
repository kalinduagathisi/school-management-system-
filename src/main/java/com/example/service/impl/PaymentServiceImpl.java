package com.example.service.impl;

import com.example.dto.requestDto.PaymentSchemeRequestDto;
import com.example.entity.PaymentSchemeEntity;
import com.example.repository.PaymentRepository;
import com.example.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public List<PaymentSchemeEntity> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public PaymentSchemeEntity findById(int schemeId) {
        return paymentRepository.findById(schemeId).orElseThrow(()->
                new IllegalArgumentException("Payment scheme with scheme id "+ schemeId+ " not found."));
    }

    @Transactional
    @Override
    public PaymentSchemeEntity save(PaymentSchemeRequestDto paymentSchemeRequestDto) {
        PaymentSchemeEntity paymentSchemeEntity = new PaymentSchemeEntity();
        paymentSchemeEntity.setSchemeName(paymentSchemeRequestDto.getSchemeName());
        paymentSchemeEntity.setSchemeType(paymentSchemeRequestDto.getSchemeType());
        paymentSchemeEntity.setPaymentPlanEntity(paymentSchemeRequestDto.getPaymentPlanEntity());

        return paymentRepository.save(paymentSchemeEntity);
    }
}
