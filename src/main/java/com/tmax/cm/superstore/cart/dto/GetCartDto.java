package com.tmax.cm.superstore.cart.dto;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tmax.cm.superstore.code.SendType;

import lombok.Builder;
import lombok.Getter;

public class GetCartDto {

    @Builder
    @Getter
    public static class Response {

        @JsonProperty("shipping/visit")
        private List<GetCartItemDto> shippingVisit;
        
        @JsonProperty("delivery/pickup")
        private List<GetCartItemDto> deliveryPickup;

        @JsonProperty("reservation")
        private List<GetCartItemDto> reservation;

        @Builder
        @Getter
        public static class GetCartItemDto {

            private UUID shopId;

            private String shopName;

            private SendType sendType;

            private UUID itemId;

            @Builder.Default
            private String itemThumbnailURL = "images/510a2ac1-7869-49c5-875b-1dfb8ea243f4.jpg";

            private String itemName;

            private Integer itemPrice;

            private UUID cartItemId;

            private Integer cartItemAmount;

            private Integer cartItemCount;

            private List<GetSelectedOptionDto> selectedOptions;

            @Builder
            @Getter
            public static class GetSelectedOptionDto {

                private UUID selectedOptionId;

                private Integer selectedOptionCount;

                private Integer selectedOptionAmount;

                private List<GetCartOptionGroupDto> cartOptionGroups;

                @Builder
                @Getter
                public static class GetCartOptionGroupDto {

                    private UUID optionGroupId;

                    private String optionGroupName;

                    private Boolean isNecessary;

                    private List<GetCartOptionDto> cartOptions;

                    @Builder
                    @Getter
                    public static class GetCartOptionDto {

                        private UUID optionId;

                        private String optionName;

                        private Integer optionPrice;

                        private Integer cartItemOptionCount;
                    }
                }
            }
        }
    }
}
