package com.example.demo;

import com.example.dto.requestDto.PaymentSchemeRequestDto;
import com.example.service.PaymentService;
import com.example.user.PaymentScheme;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// both the manager and admin has authority here

@RestController
@RequestMapping("/api/v1/management")
@Tag(name = "Management")
@RequiredArgsConstructor
public class ManagementController {

    private final PaymentService paymentService;


    // add new payment scheme
    @PostMapping("/payments")
    public ResponseEntity<PaymentScheme> createPaymentScheme(@RequestBody final PaymentSchemeRequestDto paymentSchemeRequestDto){
        PaymentScheme paymentScheme = paymentService.save(paymentSchemeRequestDto);
        return new ResponseEntity<>(paymentScheme, HttpStatus.OK);
    }


    // return list of payment schemas
    @GetMapping("/payments")
    public List<PaymentScheme> findAll(){
        return paymentService.findAll();
    }


    // get payment scheme by id
    @GetMapping("/payments/{paymentId}")
    public PaymentScheme getPaymentScheme(@PathVariable int paymentId){
        PaymentScheme paymentScheme = paymentService.findById(paymentId);

        if (paymentScheme==null){
            throw new RuntimeException("Scheme id not found - "+ paymentId);
        }
        return paymentScheme;
    }


    // delete scheme
    @DeleteMapping("/payments/{paymentId}")
    public void deletePaymentScheme(@PathVariable int paymentId){
        paymentService.deleteById(paymentId);
    }

}
