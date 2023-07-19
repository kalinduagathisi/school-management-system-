package com.example.service;

import com.example.user.PaymentScheme;

import java.util.List;

public interface PaymentService {

    List<PaymentScheme> findAll();

    PaymentScheme findById(int id);

    PaymentScheme save(PaymentScheme paymentScheme);

    void deleteById(int id);
}
