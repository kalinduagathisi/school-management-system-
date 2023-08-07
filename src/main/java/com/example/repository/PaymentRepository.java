package com.example.repository;

import com.example.entity.PaymentSchemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentSchemeEntity, Integer> {

    Optional<PaymentSchemeEntity> findBySchemeName(String schemeName);

    @Override
    Optional<PaymentSchemeEntity> findById(Integer integer);
}
