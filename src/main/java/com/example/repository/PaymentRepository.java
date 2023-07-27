package com.example.repository;

import com.example.entity.PaymentSchemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentSchemeEntity, Integer> {
}
