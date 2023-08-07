package com.example.service.impl;

import com.example.dto.Mapper;
import com.example.dto.requestDto.PaymentSchemeRequestDto;
import com.example.dto.responseDto.PaymentSchemeResponseDto;
import com.example.entity.PaymentPlanEntity;
import com.example.entity.PaymentSchemeEntity;
import com.example.exceptions.PaymentSchemeException;
import com.example.repository.PaymentRepository;
import com.example.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.constants.ApplicationConstants.RESOURCE_ALREADY_EXIST;
import static com.example.constants.ApplicationConstants.RESOURCE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;


    // Retrieve all payment schemes
    @Override
    public List<PaymentSchemeEntity> findAll() {
        return paymentRepository.findAll();
    }

    // Retrieve a payment scheme by its ID
    @Override
    public PaymentSchemeEntity findById(int schemeId) {
        try {
            Optional<PaymentSchemeEntity> byPaymentSchemeId = paymentRepository.findById(schemeId);
            PaymentSchemeEntity paymentSchemeEntity = null;
            if (byPaymentSchemeId.isPresent()) {
                paymentSchemeEntity = byPaymentSchemeId.get(); // Assign the value from Optional to the variable
            } else {
                throw new PaymentSchemeException(RESOURCE_NOT_FOUND, "Payment scheme with given Id is not found!");
            }
            return paymentSchemeEntity;
        } catch (Exception e) {
            log.error("Method findPaymentScheme : " + e.getMessage(), e);
            throw e;
        }
    }


    // Save a new payment scheme along with its associated payment plans
    @Transactional
    @Override
    public PaymentSchemeResponseDto save(PaymentSchemeRequestDto paymentSchemeRequestDto) {

        try {
            Optional<PaymentSchemeEntity> bySchemeName = paymentRepository.findBySchemeName(paymentSchemeRequestDto.getSchemeName());

            if (bySchemeName.isPresent())
                throw new PaymentSchemeException(RESOURCE_ALREADY_EXIST, "Payment scheme already exists!");

            PaymentSchemeEntity paymentSchemeEntity = new PaymentSchemeEntity();

            // Set properties from the request DTO to the entity
            paymentSchemeEntity.setSchemeName(paymentSchemeRequestDto.getSchemeName());
            paymentSchemeEntity.setSchemeType(paymentSchemeRequestDto.getSchemeType());

            List<Map<String, Object>> paymentPlanEntityList = paymentSchemeRequestDto.getPaymentPlanEntityList();
            if (paymentPlanEntityList != null) {
                for (Map<String, Object> paymentPlanData : paymentPlanEntityList) {
                    String feeType = (String) paymentPlanData.get("feeType");
                    Double amount = (Double) paymentPlanData.get("amount");

                    PaymentPlanEntity paymentPlanEntity = new PaymentPlanEntity();
                    paymentPlanEntity.setFeeType(feeType);
                    paymentPlanEntity.setAmount(amount);
                    paymentPlanEntity.setPaymentSchemeEntity(paymentSchemeEntity);

                    // Add the payment plan entity to the payment scheme entity
                    paymentSchemeEntity.addPaymentPlanEntity(paymentPlanEntity);
                }
            }

            // Save the payment scheme entity along with associated payment plans
            PaymentSchemeEntity savedPaymentScheme = paymentRepository.save(paymentSchemeEntity);

            // Convert the saved payment scheme entity to a response DTO
            return Mapper.paymentSchemeToPaymentSchemeDto(savedPaymentScheme);

        } catch (Exception e) {
            log.error("Method addPaymentScheme : " + e.getMessage(), e);
            throw e;
        }

    }

}
