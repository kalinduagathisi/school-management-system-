package com.example.dto.requestDto;

import com.example.enums.StudentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDto {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private Integer schemeId;

    @NotNull
    private StudentStatus studentStatus;
}
