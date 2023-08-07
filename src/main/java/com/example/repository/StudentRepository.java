package com.example.repository;

import com.example.dto.requestDto.StudentRequestDto;
import com.example.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    @Override
    Optional<StudentEntity> findById(Integer integer);

    Optional<StudentEntity> findByEmail(String email);

    // Native SQL query to filter students by birth month and year
    @Query(value = "SELECT * FROM student_table WHERE MONTH(date_of_birth) = :birthMonth AND YEAR(date_of_birth) = :birthYear", nativeQuery = true)
    List<StudentEntity> findByBirthMonthAndYear(int birthMonth, int birthYear);

    // Native SQL query to filter students by birthdate range
    @Query(value = "SELECT * FROM student_table WHERE date_of_birth BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<StudentEntity> findByBirthdateBetween(Date startDate, Date endDate);
}


