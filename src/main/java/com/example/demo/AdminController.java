package com.example.demo;

import com.example.dto.requestDto.StudentRequestDto;
import com.example.dto.responseDto.StudentResponseDto;
import com.example.service.PaymentService;
import com.example.service.StudentService;
import com.example.user.PaymentScheme;
import com.example.user.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final StudentService studentService;
    private final PaymentService paymentService;


    // add new students
    @PostMapping("/students")
    public ResponseEntity<Student> addStudent(@RequestBody final StudentRequestDto studentRequestDto){
        Student student = studentService.addStudent(studentRequestDto);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    // return list of students
    @GetMapping("/students")
    public List<Student> findAll(){
        return studentService.getAllStudents();
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudentById(@PathVariable int id){
        studentService.deleteById(id);
    }

}
