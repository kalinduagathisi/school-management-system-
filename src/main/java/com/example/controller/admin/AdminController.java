package com.example.controller.admin;

import com.example.dto.requestDto.StudentRequestDto;
import com.example.dto.responseDto.StudentResponseDto;
import com.example.entity.StudentEntity;
import com.example.service.PaymentService;
import com.example.service.StudentService;
import jakarta.validation.Valid;
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

    // Add new students
    @PostMapping("/students")
    public ResponseEntity<StudentEntity> addStudent( @RequestBody @Valid  final StudentRequestDto studentRequestDto) {
        StudentEntity studentEntity = studentService.addStudent(studentRequestDto);
        return new ResponseEntity<>(studentEntity, HttpStatus.CREATED);
    }

    // Return a list of all students
    @GetMapping("/students")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")  // Method level overrides class level
    public List<StudentEntity> findAll() {
        return studentService.getAllStudents();
    }


    // get student information by ID
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable int id) {
        StudentEntity byStudentId = studentService.getStudentById(id);
        return new ResponseEntity<>(byStudentId, HttpStatus.OK);
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
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable int id, @RequestBody final StudentRequestDto studentRequestDto) {
//        StudentResponseDto studentResponseDto = studentService.updateStudent(id, studentRequestDto);
//        return new ResponseEntity<>(studentResponseDto, HttpStatus.OK);
        StudentEntity studentUpdate = studentService.updateStudent(id, studentRequestDto);
        return new ResponseEntity<>(studentUpdate, HttpStatus.OK);
    }
}
