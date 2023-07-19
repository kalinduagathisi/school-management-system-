package com.example.payment;

import com.example.user.PaymentScheme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentScheme, Integer> {
}
