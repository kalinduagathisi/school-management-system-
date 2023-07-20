package com.example.service;

import com.example.dto.requestDto.PaymentSchemeRequestDto;
import com.example.payment.PaymentRepository;
import com.example.user.PaymentScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

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
        paymentScheme.setFeeType(paymentSchemeRequestDto.getFeeType());
        paymentScheme.setAmount(paymentSchemeRequestDto.getAmount());
        return paymentRepository.save(paymentScheme);
    }


    @Override
    public void deleteById(int id) {
    }
}
