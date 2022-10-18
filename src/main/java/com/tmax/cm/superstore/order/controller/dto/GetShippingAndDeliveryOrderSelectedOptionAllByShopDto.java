package com.tmax.cm.superstore.order.controller.dto;

import java.util.List;
import java.util.UUID;

import com.tmax.cm.superstore.code.ShippingType;

import lombok.Builder;
import lombok.Getter;

public class GetShippingAndDeliveryOrderSelectedOptionAllByShopDto {

    @Builder
    @Getter
    public static class Response {

        private List<GetSelectedOptionDto> orderSelectedOptions;

        @Builder
        @Getter
        public static class GetSelectedOptionDto {

            private UUID orderSelectedOptionId;

            private UUID itemId;

            private String itemName;

            private Integer itemPrice;

            private Integer count;

            private Integer amount;

            private String recipient;

            private String mobile;

            private String address;

            private String requests;

            private ShippingType shippingType;

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
}
