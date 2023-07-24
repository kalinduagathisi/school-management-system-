package com.example.service;

import com.example.dto.requestDto.PaymentSchemeRequestDto;
import com.example.payment.PaymentRepository;
import com.example.user.PaymentPlan;
import com.example.user.PaymentScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    @Override
    public List<PaymentScheme> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public PaymentScheme findById(int schemeId) {
        return paymentRepository.findById(schemeId).orElseThrow(()->
                new IllegalArgumentException("Payment scheme with scheme id "+ schemeId+ " not found."));
    }

    @Transactional
    @Override
    public PaymentScheme save(PaymentSchemeRequestDto paymentSchemeRequestDto) {
        PaymentScheme paymentScheme = new PaymentScheme();
        paymentScheme.setSchemeName(paymentSchemeRequestDto.getSchemeName());
        paymentScheme.setSchemeType(paymentSchemeRequestDto.getSchemeType());
        paymentScheme.setPaymentPlan(paymentSchemeRequestDto.getPaymentPlan());

        return paymentRepository.save(paymentScheme);
    }
}
