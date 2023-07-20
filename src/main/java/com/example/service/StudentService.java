package com.example.service;


import com.example.dto.requestDto.StudentRequestDto;
import com.example.dto.responseDto.StudentResponseDto;
import com.example.user.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    Student addStudent(StudentRequestDto studentRequestDto);

    List<Student> getAllStudents();

    Student getStudentById(int id);

    void deleteById(int id);

    StudentResponseDto updateStudent(int studentId, StudentRequestDto studentRequestDto);

}
