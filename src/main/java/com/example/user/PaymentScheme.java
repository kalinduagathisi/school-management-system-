package com.example.user;

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
public class PaymentScheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheme_id")
    private Integer schemeId; // primary key: scheme

    @Column(name = "scheme_name")
    private String schemeName;

    private String schemeType;

    private String feeType;

    private double amount;

    @OneToOne(mappedBy = "paymentScheme", cascade = CascadeType.ALL)
    private Student student;
}

