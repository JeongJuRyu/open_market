package com.tmax.cm.superstore.payment.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.payment.entity.Payment;
import com.tmax.cm.superstore.payment.service.dto.CreatePaymentDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PaymentService {

    @Transactional
    public Payment create(CreatePaymentDto createPaymentDto) {
        Payment payment = Payment.builder()
                .paymentType(createPaymentDto.getPaymentType())
                .paymentAmount(createPaymentDto.getPaymentAmount())
                .build();

        return payment;
    }
}
