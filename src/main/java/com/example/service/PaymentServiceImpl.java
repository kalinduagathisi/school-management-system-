package com.example.service;

import com.example.payment.PaymentRepository;
import com.example.user.PaymentScheme;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

    private PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<PaymentScheme> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public PaymentScheme findById(int id) {
        return null;
    }

    public PaymentScheme save(PaymentScheme paymentScheme) {
        return paymentRepository.save(paymentScheme);
    }

    @Override
    public void deleteById(int id) {

    }
}
