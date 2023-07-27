package com.example.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "student_table")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;

//    @JsonIgnore
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="schemeId")
//    @JsonIdentityReference(alwaysAsId=true)  // returns only the id of the required entity
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "scheme_id")
    private PaymentSchemeEntity paymentSchemeEntity;

}

