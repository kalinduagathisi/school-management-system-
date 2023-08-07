package com.example.controller.management;

import com.example.dto.requestDto.PaymentSchemeRequestDto;
import com.example.dto.responseDto.PaymentSchemeResponseDto;
import com.example.entity.PaymentSchemeEntity;
import com.example.entity.StudentEntity;
import com.example.service.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// both manager and the admin have authority here

@RestController
@RequestMapping("/api/v1/management")
@Tag(name = "Management")
@RequiredArgsConstructor
public class ManagementController {

    private final PaymentService paymentService;


    // add new payment scheme
    @PostMapping("/payments")
    public ResponseEntity<PaymentSchemeResponseDto> createPaymentScheme(@RequestBody @Valid final PaymentSchemeRequestDto paymentSchemeRequestDto){
        PaymentSchemeResponseDto paymentSchemeResponseDto = paymentService.save(paymentSchemeRequestDto);
        return new ResponseEntity<>(paymentSchemeResponseDto, HttpStatus.OK);
    }


    // return list of payment schemas
    @GetMapping("/payments")
    public List<PaymentSchemeEntity> findAll(){
        return paymentService.findAll();
    }


    // get student information by ID
    @GetMapping("/payments/{id}")
    public ResponseEntity<PaymentSchemeEntity> getPaymentSchemeById(@PathVariable int id) {
        PaymentSchemeEntity byPaymentSchemeId = paymentService.findById(id);
        return new ResponseEntity<>(byPaymentSchemeId, HttpStatus.OK);
    }

}
