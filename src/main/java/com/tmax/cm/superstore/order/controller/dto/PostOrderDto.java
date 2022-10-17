package com.tmax.cm.superstore.order.controller.dto;

import java.util.List;
import java.util.UUID;

import com.tmax.cm.superstore.code.PaymentType;
import com.tmax.cm.superstore.payment.service.dto.CreatePaymentDto;

import lombok.Getter;

public class PostOrderDto {
    @Getter
    public static class Request implements CreatePaymentDto {

        private PaymentType paymentType;

        private Integer paymentAmount;

        private List<UUID> cartItemIds;

        private ShippingDto shippingRecipientInfo;

        private PickupDto visitRecipientInfo;

        private ShippingDto deliveryRecipientInfo;

        private PickupDto pickupRecipientInfo;

        @Getter
        public static class ShippingDto {

            private String recipient;

            private String address;

            private String mobile;

            private String requests;
        }

        @Getter
        public static class PickupDto {

            private String recipient;

            private String mobile;

            private String requests;
        }
    }
}
