package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "payment_plan")
public class PaymentPlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentPlanId;
    private String feeType;
    private double amount;
}
