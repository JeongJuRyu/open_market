package com.tmax.cm.superstore.order.controller.dto;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.tmax.cm.superstore.code.PaymentType;
import com.tmax.cm.superstore.payment.service.dto.CreatePaymentDto;

import lombok.Getter;

public class PostOrderDto {
    @Getter
    @NotNull
    public static class Request implements CreatePaymentDto {

        private PaymentType paymentType;

        private Integer paymentAmount;

        private List<UUID> cartItemIds;
    }
}
