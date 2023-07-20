package com.example.service;

import com.example.dto.Mapper;
import com.example.dto.requestDto.StudentRequestDto;
import com.example.dto.responseDto.StudentResponseDto;
import com.example.user.PaymentScheme;
import com.example.user.Student;
import com.example.user.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImplementation implements StudentService {

    private final StudentRepository studentRepository;
    private final PaymentService paymentService;

    @Autowired
    public StudentServiceImplementation(StudentRepository studentRepository, PaymentService paymentService){
        this.studentRepository = studentRepository;
        this.paymentService = paymentService;
    }


    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        Optional<Student> result = studentRepository.findById(id);
        Student student=null;
        if (result.isPresent()){
            student=result.get();
        }
        return student;
    }

    @Transactional
    @Override
    public Student addStudent(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        student.setEmail(studentRequestDto.getEmail());
        student.setDateOfBirth(studentRequestDto.getDateOfBirth());

        if (studentRequestDto.getSchemeId()==null){
            return studentRepository.save(student);
        }
        PaymentScheme paymentScheme = paymentService.findById(studentRequestDto.getSchemeId());
        student.setPaymentScheme(paymentScheme);
        return studentRepository.save(student);

    }

    @Override
    @Transactional
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }
}
