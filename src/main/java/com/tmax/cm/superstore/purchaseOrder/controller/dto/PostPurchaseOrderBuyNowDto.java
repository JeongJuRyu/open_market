package com.tmax.cm.superstore.purchaseOrder.controller.dto;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tmax.cm.superstore.cart.service.dto.CreateCartItemDto;
import com.tmax.cm.superstore.cart.service.dto.CreateCartOptionDto;
import com.tmax.cm.superstore.cart.service.dto.CreateCartOptionGroupDto;
import com.tmax.cm.superstore.cart.service.dto.CreateSelectedOptionDto;
import com.tmax.cm.superstore.code.SendType;

import lombok.Builder;
import lombok.Getter;

public class PostPurchaseOrderBuyNowDto {

    @Getter
    @NotNull
    public static class Request implements CreateCartItemDto {

        @NotNull
        @JsonProperty("itemId")
        private UUID id;

        @NotNull
        private SendType sendType;

        @NotNull
        private List<PostSelectedOptionDto> selectedOptions;

        @Getter
        @NotNull
        public static class PostSelectedOptionDto implements CreateSelectedOptionDto {

            @NotNull
            private Integer count;

            private List<PostCartOptionGroupDto> cartOptionGroups;

            @Getter
            @NotNull
            public static class PostCartOptionGroupDto implements CreateCartOptionGroupDto {

                @JsonProperty("optionGroupId")
                private UUID id;

                private List<PostCartOptionDto> cartOptions;

                @Getter
                public static class PostCartOptionDto implements CreateCartOptionDto {

                    @JsonProperty("optionId")
                    private UUID id;

                    private Integer count;
                }
            }
        }
    }

    @Builder
    @Getter
    public static class Response {

        private PaymentInfoDto paymentInfo;

        private List<ShippingDto> shippings;

        private List<VisitDto> visits;

        private List<ShippingDto> deliveries;

        private List<VisitDto> pickups;

        @Builder
        @Getter
        public static class PaymentInfoDto {

            private Integer totalItemAmount;

            private Integer totalShippingFee;

            private Integer totalPaymentAmount;
        }

        @Builder
        @Getter
        public static class ShippingDto {

            private UUID shopId;

            private String shopName;

            private Integer cartItemAmount;

            private List<CartItemDto> cartItems;
        }

        @Builder
        @Getter
        public static class VisitDto {

            private UUID shopId;

            private String shopName;

            private String shopAddress;

            private Integer cartItemAmount;

            private List<CartItemDto> cartItems;
        }

        @Builder
        @Getter
        public static class CartItemDto {

            private UUID cartItemId;

            private UUID itemId;

            private String itemThumbnailURL;

            private String itemName;

            private Integer cartItemAmount;

            private Integer shippingFee;

            private List<SelectedOptionDto> selectedOptions;

            @Builder
            @Getter
            public static class SelectedOptionDto {

                private Integer selectedOptionAmount;

                private Integer selectedOptionCount;

                private List<CartOptionGroupDto> cartOptionGroups;

                @Builder
                @Getter
                public static class CartOptionGroupDto {

                    private String optionGroupName;

                    private Boolean isNecessary;

                    private List<CartOptionDto> cartOptions;

                    @Builder
                    @Getter
                    public static class CartOptionDto {

                        private String optionName;

                        private String optionPrice;

                        private Integer cartOptionCount;
                    }
                }
            }
        }
    }
}
