package com.example.controller.admin;

import com.example.dto.requestDto.StudentRequestDto;
import com.example.dto.responseDto.StudentResponseDto;
import com.example.entity.StudentEntity;
import com.example.service.PaymentService;
import com.example.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final StudentService studentService;
    private final PaymentService paymentService;

    // Add new students
    @PostMapping("/students")
    public ResponseEntity<StudentEntity> addStudent(@RequestBody final StudentRequestDto studentRequestDto) {
        StudentEntity studentEntity = studentService.addStudent(studentRequestDto);
        return new ResponseEntity<>(studentEntity, HttpStatus.OK);
    }

    // Return a list of all students
    @GetMapping("/students")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")  // Method level overrides class level
    public List<StudentEntity> findAll() {
        return studentService.getAllStudents();
    }

    // Filter students based on birthMonth and birthYear
    @GetMapping("/students/filter")
    public List<StudentResponseDto> getStudentsByBirthMonthAndYear(
            @RequestParam int birthMonth,
            @RequestParam int birthYear
    ) {
        return studentService.getStudentsByBirthMonthAndYear(birthMonth, birthYear);
    }

    // Filter students based on a date range
    @GetMapping("/students/filter/range")
    public List<StudentEntity> getStudentsByBirthdateRange(
            @RequestParam Date startDate,
            @RequestParam Date endDate
    ) {
        return studentService.getStudentsByBirthdateRange(startDate, endDate);
    }

    // Delete a student by ID
    @DeleteMapping("/students/{id}")
    public void deleteStudentById(@PathVariable int id) {
        studentService.deleteById(id);
    }


    // Update student information by ID
    @PutMapping("/students/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable int id, @RequestBody final StudentRequestDto studentRequestDto) {
        StudentResponseDto studentResponseDto = studentService.updateStudent(id, studentRequestDto);
        return new ResponseEntity<>(studentResponseDto, HttpStatus.OK);
    }
}
