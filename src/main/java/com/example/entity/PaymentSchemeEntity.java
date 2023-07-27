package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "payment_scheme")
public class PaymentSchemeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheme_id")
    private Integer schemeId; // Primary key: scheme

    @Column(name = "scheme_name")
    private String schemeName;

    private String schemeType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentSchemeEntity", orphanRemoval = true)
    private List<PaymentPlanEntity> paymentPlanEntity = new ArrayList<>(); // Initialize the list

    // Helper method to add PaymentPlanEntity and set the parent PaymentScheme
    public void addPaymentPlanEntity(PaymentPlanEntity planEntity) {
        planEntity.setPaymentSchemeEntity(this);
        this.paymentPlanEntity.add(planEntity);
    }

}
