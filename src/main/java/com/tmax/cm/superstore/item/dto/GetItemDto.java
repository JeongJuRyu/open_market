package com.tmax.cm.superstore.item.dto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tmax.cm.superstore.code.ShippingChargeType;
import com.tmax.cm.superstore.item.code.ItemState;
import com.tmax.cm.superstore.code.SendType;

import lombok.Builder;
import lombok.Getter;

public class GetItemDto {
    
    @Builder
    @Getter
    public static class Response {
        
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

        private List<GetItemImageDto> itemImages;

        private List<GetOptionGroupDto> optionGroups;

        @Builder
        @Getter
        public static class GetItemImageDto {
            private String fileId;
        }

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
