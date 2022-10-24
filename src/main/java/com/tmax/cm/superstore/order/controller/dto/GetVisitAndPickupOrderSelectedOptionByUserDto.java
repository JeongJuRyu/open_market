package com.tmax.cm.superstore.order.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.tmax.cm.superstore.code.PaymentType;
import com.tmax.cm.superstore.code.PickupType;

import lombok.Builder;
import lombok.Getter;

public class GetVisitAndPickupOrderSelectedOptionByUserDto {

    @Builder
    @Getter
    public static class Response {

        private UUID orderSelectedOptionId;

        private UUID sellerId;

        private String sellerName;

        private UUID itemId;

        private String itemName;

        @Builder.Default // TODO remove defualt image
        private String itemImage = "http://192.168.159.42:8888/images/58c04a256d774a1a8d6c8f3659eeadbf";

        private boolean isReviewExist;

        private Integer itemPrice;

        private Integer count;

        private Integer amount;

        private String recipient;

        private String mobile;

        private String address;

        private String requests;

        private PickupType pickupType;

        private PaymentType paymentType;

        private LocalDateTime createdAt;

        private List<GetOrderOptionGroupDto> orderOptionGroups;

        @Builder
        @Getter
        public static class GetOrderOptionGroupDto {

            private String optionGroupName;

            private List<GetOrderOptionDto> orderOptions;

            @Builder
            @Getter
            public static class GetOrderOptionDto {

                private String optionName;

                private Integer count;

                private Integer price;
            }
        }
    }
}
