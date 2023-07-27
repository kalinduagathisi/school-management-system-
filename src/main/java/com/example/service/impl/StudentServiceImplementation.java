package com.example.service.impl;

import com.example.dto.Mapper;
import com.example.dto.requestDto.StudentRequestDto;
import com.example.dto.responseDto.StudentResponseDto;
import com.example.entity.PaymentSchemeEntity;
import com.example.entity.StudentEntity;
import com.example.repository.StudentRepository;
import com.example.service.PaymentService;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public StudentEntity getStudentById(int id) {
        Optional<StudentEntity> result = studentRepository.findById(id);
        StudentEntity studentEntity =null;
        if (result.isPresent()){
            studentEntity =result.get();
        }
        return studentEntity;
    }

    @Transactional
    @Override
    public StudentEntity addStudent(StudentRequestDto studentRequestDto) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setFirstName(studentRequestDto.getFirstName());
        studentEntity.setLastName(studentRequestDto.getLastName());
        studentEntity.setEmail(studentRequestDto.getEmail());
        studentEntity.setDateOfBirth(studentRequestDto.getDateOfBirth());

        PaymentSchemeEntity paymentSchemeEntity = paymentService.findById(studentRequestDto.getSchemeId());
        studentEntity.setPaymentSchemeEntity(paymentSchemeEntity);

        if (studentRequestDto.getSchemeId()==null){
//            return studentRepository.save(studentEntity);
            throw new IllegalArgumentException("Payment scheme couldn't be null.");
        }
        else {
            return studentRepository.save(studentEntity);
        }
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    @Override
    public StudentResponseDto updateStudent(int studentId, StudentRequestDto studentRequestDto) {
        StudentEntity studentEntityToBeUpdated = getStudentById(studentId);

        studentEntityToBeUpdated.setFirstName(studentRequestDto.getFirstName());
        studentEntityToBeUpdated.setLastName(studentRequestDto.getLastName());
        studentEntityToBeUpdated.setEmail(studentRequestDto.getEmail());
        studentEntityToBeUpdated.setDateOfBirth(studentRequestDto.getDateOfBirth());

        PaymentSchemeEntity paymentSchemeEntity = paymentService.findById(studentRequestDto.getSchemeId());
        studentEntityToBeUpdated.setPaymentSchemeEntity(paymentSchemeEntity);

        return Mapper.studentToStudentResponseDto(studentEntityToBeUpdated);
    }
}
