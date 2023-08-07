package com.example.service;


import com.example.dto.requestDto.StudentRequestDto;
import com.example.dto.responseDto.StudentResponseDto;
import com.example.entity.StudentEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface StudentService {

    StudentEntity addStudent(StudentRequestDto studentRequestDto);

    List<StudentEntity> getAllStudents();

    StudentEntity getStudentById(int id);

    void deleteById(int id);

    StudentEntity updateStudent(int studentId, StudentRequestDto studentRequestDto);

    List<StudentResponseDto> getStudentsByBirthMonthAndYear(int birthMonth, int birthYear);

    public List<StudentEntity> getStudentsByBirthdateRange(Date startDate, Date endDate);

}
