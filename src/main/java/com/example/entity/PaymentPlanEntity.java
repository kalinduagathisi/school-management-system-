package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @Column(name = "payment_plan_id")
    private Integer paymentPlanId;
    private String feeType;
    private double amount;

    //    @JsonIgnore
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "schemeId")
    @JsonIdentityReference(alwaysAsId = true)  // returns only the id of the required entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_scheme_id")
    private PaymentSchemeEntity paymentSchemeEntity;
}
