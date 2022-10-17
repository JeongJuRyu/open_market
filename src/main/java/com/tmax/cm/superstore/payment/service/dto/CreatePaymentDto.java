package com.tmax.cm.superstore.payment.service.dto;

import com.tmax.cm.superstore.code.PaymentType;

public interface CreatePaymentDto {

    PaymentType getPaymentType();

    Integer getPaymentAmount();
}
