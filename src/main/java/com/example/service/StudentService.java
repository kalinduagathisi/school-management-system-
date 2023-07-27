package com.example.service;


import com.example.dto.requestDto.StudentRequestDto;
import com.example.dto.responseDto.StudentResponseDto;
import com.example.entity.StudentEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    StudentEntity addStudent(StudentRequestDto studentRequestDto);

    List<StudentEntity> getAllStudents();

    StudentEntity getStudentById(int id);

    void deleteById(int id);

    StudentResponseDto updateStudent(int studentId, StudentRequestDto studentRequestDto);

}
