package com.tmax.cm.superstore.item.dto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.tmax.cm.superstore.code.ShippingChargeType;
import com.tmax.cm.superstore.item.code.ItemState;
import com.tmax.cm.superstore.code.SendType;

import lombok.*;

public class PostItemDto {

    @Getter
    public static class Request {

        @NotNull
        private String name;

        @NotNull
        @PositiveOrZero
        private Integer price;

        private Set<SendType> possibleSendType;

        private List<PostOptionGroupDto> optionGroups;

        @NotNull
        private Long categoryId;

        @NotNull
        private ItemState itemState;

        @NotNull
        private ShippingChargeType shippingChargeType;

        @NotNull
        private Integer shippingCharge;

        private String description;

        private Integer returnCharge;

        private String returnAddress;

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

    @Builder
    @Getter
    public static class Response {

        private UUID itemId;
    }
}
