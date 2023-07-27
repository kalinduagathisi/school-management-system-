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
@Table(name = "payment_scheme")
public class PaymentSchemeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheme_id")
    private Integer schemeId; // primary key: scheme

    @Column(name = "scheme_name")
    private String schemeName;

    private String schemeType;

    @JoinColumn(name = "payment_plan_id")
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private PaymentPlanEntity paymentPlanEntity;

//    @OneToMany(mappedBy = "paymentSchemeEntity", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
//    private List<StudentEntity> students;

}

