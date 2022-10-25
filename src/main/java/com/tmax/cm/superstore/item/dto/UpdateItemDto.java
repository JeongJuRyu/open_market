package com.tmax.cm.superstore.item.dto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.tmax.cm.superstore.code.SendType;
import com.tmax.cm.superstore.code.ShippingChargeType;
import com.tmax.cm.superstore.item.code.ItemState;

import lombok.Builder;
import lombok.Getter;

public class UpdateItemDto {

    @Getter
    public static class Request {

        private String name;

        private Integer price;

        private Set<SendType> possibleSendType;

        private ItemState itemState;

        private ShippingChargeType shippingChargeType;

        private Integer shippingCharge;

        private Long categoryId;

        private String description;

        private Integer returnCharge;

        private String returnAddress;

        private List<PostOptionGroupDto> optionGroups;

        @Getter
        public static class PostOptionGroupDto {

            private String name;

            private Boolean isNecessary;

            private List<PostOptionDto> options;

            @Getter
            public static class PostOptionDto {

                private String name;

                private Integer price;

                private String description;
            }
        }
    }

    @Getter
    @Builder
    public static class Response {
        UUID itemId;
    }

}
