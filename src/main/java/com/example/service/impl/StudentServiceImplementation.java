package com.example.service.impl;

import com.example.dto.Mapper;
import com.example.dto.requestDto.StudentRequestDto;
import com.example.dto.responseDto.StudentResponseDto;
import com.example.entity.PaymentSchemeEntity;
import com.example.entity.StudentEntity;
import com.example.exceptions.StudentException;
import com.example.repository.StudentRepository;
import com.example.service.PaymentService;
import com.example.service.StudentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.constants.ApplicationConstants.RESOURCE_ALREADY_EXIST;
import static com.example.constants.ApplicationConstants.RESOURCE_NOT_FOUND;

@Service
@Log4j2

public class StudentServiceImplementation implements StudentService {

    private final StudentRepository studentRepository;
    private final PaymentService paymentService;

    @Autowired
    public StudentServiceImplementation(StudentRepository studentRepository, PaymentService paymentService) {
        this.studentRepository = studentRepository;
        this.paymentService = paymentService;
    }

    // Retrieve all students from the repository
    @Override
    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    // Retrieve a student by their ID
    @Override
    public StudentEntity getStudentById(int id) {
        Optional<StudentEntity> byId = studentRepository.findById(id);
        StudentEntity studentEntity = null;
        if (byId.isPresent()) {
            studentEntity = byId.get();
        } else {
            throw new StudentException(RESOURCE_NOT_FOUND, "Student with given Id not found");
        }
        return studentEntity;
    }

    // Add a new student
    @Transactional
    @Override
    public StudentEntity addStudent(StudentRequestDto studentRequestDto) {
        try {
            // Check if a student with the given email already exists
            Optional<StudentEntity> byUserEmail = studentRepository.findByEmail(studentRequestDto.getEmail());

            if (byUserEmail.isPresent())
                throw new StudentException(RESOURCE_ALREADY_EXIST, "User email already exists");

            // Create a new student entity and populate it with data from the request DTO
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setFirstName(studentRequestDto.getFirstName());
            studentEntity.setLastName(studentRequestDto.getLastName());
            studentEntity.setEmail(studentRequestDto.getEmail());
            studentEntity.setDateOfBirth(studentRequestDto.getDateOfBirth());
            studentEntity.setStudentStatus(studentRequestDto.getStudentStatus());

            // Get a payment scheme entity by ID from the payment service
            PaymentSchemeEntity paymentSchemeEntity = paymentService.findById(studentRequestDto.getSchemeId());
            studentEntity.setPaymentSchemeEntity(paymentSchemeEntity);

            // Save the new student entity to the repository
            return studentRepository.save(studentEntity);
        } catch (Exception e) {
            log.error("Method addStudent : " + e.getMessage(), e);
            throw e;
        }
    }

    // Delete a student by their ID
    @Override
    public void deleteById(int id) {
        try {
            Optional<StudentEntity> byStudentId = studentRepository.findById(id);

            if (!byStudentId.isPresent())
                throw new StudentException(RESOURCE_NOT_FOUND, "Student with given ID not found!");

            studentRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Method deleteById : " + e.getMessage(), e);
            throw e;
        }
    }

    // Update a student's information
    @Override
    @Transactional
    public StudentEntity updateStudent(int studentId, StudentRequestDto studentRequestDto) {
        try {
            // first check if a student exists with the given id
            Optional<StudentEntity> byStudentId = studentRepository.findById(studentId);
            if (byStudentId.isPresent()) {
                StudentEntity studentEntityToBeUpdated = getStudentById(studentId);

                // Update student's details based on the request DTO
                studentEntityToBeUpdated.setFirstName(studentRequestDto.getFirstName());
                studentEntityToBeUpdated.setLastName(studentRequestDto.getLastName());
                studentEntityToBeUpdated.setEmail(studentRequestDto.getEmail());
                studentEntityToBeUpdated.setDateOfBirth(studentRequestDto.getDateOfBirth());
                studentEntityToBeUpdated.setStudentStatus(studentRequestDto.getStudentStatus());

                // Get a payment scheme entity by ID from the payment service
                PaymentSchemeEntity paymentSchemeEntity = paymentService.findById(studentRequestDto.getSchemeId());
                studentEntityToBeUpdated.setPaymentSchemeEntity(paymentSchemeEntity);

                // Convert the updated student entity to a response DTO
//                return Mapper.studentToStudentResponseDto(studentEntityToBeUpdated);
                return studentEntityToBeUpdated;
            } else {
                throw new StudentException(RESOURCE_NOT_FOUND, "student with the given Id not found!");
            }
        } catch (Exception e) {
            log.error("Method updateStudent : " + e.getMessage(), e);
            throw e;
        }

    }

    // Retrieve students by birth month and year
    public List<StudentResponseDto> getStudentsByBirthMonthAndYear(int birthMonth, int birthYear) {
        return Mapper.studentsToStudentsResponseDto(studentRepository.findByBirthMonthAndYear(birthMonth, birthYear));
    }

    // Retrieve students by birthdate range
    public List<StudentEntity> getStudentsByBirthdateRange(Date startDate, Date endDate) {
        return studentRepository.findByBirthdateBetween(startDate, endDate);
    }
}
