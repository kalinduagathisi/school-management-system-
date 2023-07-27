package com.example.controller.management;

import com.example.dto.requestDto.PaymentSchemeRequestDto;
import com.example.entity.PaymentSchemeEntity;
import com.example.service.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public ResponseEntity<PaymentSchemeEntity> createPaymentScheme(@RequestBody final PaymentSchemeRequestDto paymentSchemeRequestDto){
        PaymentSchemeEntity paymentSchemeEntity = paymentService.save(paymentSchemeRequestDto);
        return new ResponseEntity<>(paymentSchemeEntity, HttpStatus.OK);
    }


    // return list of payment schemas
    @GetMapping("/payments")
    public List<PaymentSchemeEntity> findAll(){
        return paymentService.findAll();
    }


    // get payment scheme by id
    @GetMapping("/payments/{paymentId}")
    public PaymentSchemeEntity getPaymentScheme(@PathVariable int paymentId){
        PaymentSchemeEntity paymentSchemeEntity = paymentService.findById(paymentId);

        if (paymentSchemeEntity ==null){
            throw new RuntimeException("Scheme id not found - "+ paymentId);
        }
        return paymentSchemeEntity;
    }

}
