package com.tmax.cm.superstore.cart.dto;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.tmax.cm.superstore.code.SendType;

import lombok.Builder;
import lombok.Getter;

public class GetCartItemDto {

    @Builder
    @Getter
    public static class Response {

        @NotNull
        private UUID shopId;

        @NotNull
        private String shopName;

        @NotNull
        private SendType sendType;

        @NotNull
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

                private UUID cartOptionGroupId;

                private UUID optionGroupId;

                private String optionGroupName;

                private Boolean isNecessary;

                private List<GetCartOptionDto> cartOptions;

                @Builder
                @Getter
                public static class GetCartOptionDto {

                    private UUID cartOptionId;

                    private UUID optionId;

                    private String optionName;

                    private Integer optionPrice;

                    private Integer cartItemOptionCount;
                }
            }
        }

        public void calculate() {
            this.cartItemAmount = 0;
            this.cartItemCount = 0;

            for (GetSelectedOptionDto selectedOption : selectedOptions) {
                selectedOption.selectedOptionAmount = 0;

                for (GetCartItemDto.Response.GetSelectedOptionDto.GetCartOptionGroupDto cartOptionGroup : selectedOption.cartOptionGroups) {
                    for (GetCartItemDto.Response.GetSelectedOptionDto.GetCartOptionGroupDto.GetCartOptionDto cartOption : cartOptionGroup.cartOptions) {
                        selectedOption.selectedOptionAmount += cartOption.optionPrice
                                * cartOption.cartItemOptionCount;
                    }
                }

                selectedOption.selectedOptionAmount += this.itemPrice;
                this.cartItemAmount += selectedOption.selectedOptionAmount * selectedOption.selectedOptionCount;
                this.cartItemCount += selectedOption.selectedOptionCount;
            }
        }
    }
}
