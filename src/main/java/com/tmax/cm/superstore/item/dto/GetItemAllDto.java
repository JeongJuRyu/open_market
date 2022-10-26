package com.tmax.cm.superstore.item.dto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tmax.cm.superstore.code.SendType;

import com.tmax.cm.superstore.code.ShippingChargeType;
import com.tmax.cm.superstore.item.code.ItemState;
import lombok.Builder;
import lombok.Getter;

public class GetItemAllDto {
    @Builder
    @Getter
    public static class Response {

        private List<GetItemDto> items;

        @Builder
        @Getter
        public static class GetItemDto {

            private UUID shopId;

            private String shopName;

            private Set<SendType> possibleSendType;

            private UUID itemId;

            private String itemName;

            private Integer itemPrice;

            private Long categoryId;

            private ItemState itemState;

            private ShippingChargeType shippingChargeType;

            private Integer shippingCharge;

            private String returnAddress;

            private Integer returnCharge;

            private String description;

            @JsonProperty("necessaryOptionGroups")
            private List<GetOptionGroupDto> necessaryOptionGroups;

            @JsonProperty("optionalOptionGroups")
            private List<GetOptionGroupDto> optionalOptionGroups;

            @Builder
            @Getter
            public static class GetOptionGroupDto {

                private UUID id;

                private String name;

                private Boolean isNecessary;

                private List<GetOptionDto> options;

                @Builder
                @Getter
                public static class GetOptionDto {

                    private UUID id;

                    private String name;

                    private Integer price;

                    private String description;
                }
            }
        }
    }
}
