package com.tmax.cm.superstore.purchaseOrder.controller.dto;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public class PostPurchaseOrderCartDto {

    @Getter
    public static class Request {

        List<UUID> cartItemIds;
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

            private UUID sellerId;

            private String shopName;

            private Integer cartItemAmount;

            private List<CartItemDto> cartItems;
        }

        @Builder
        @Getter
        public static class VisitDto {

            private UUID sellerId;

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
