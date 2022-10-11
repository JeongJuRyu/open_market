package com.tmax.cm.superstore.purchaseOrder.service.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tmax.cm.superstore.cart.entity.CartItem;
import com.tmax.cm.superstore.cart.entity.SelectedOption;
import com.tmax.cm.superstore.shop.entity.Shop;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PurchaseOrderDto {

    private PaymentInfoDto paymentInfo;

    private CartItemDtosMap shippings;

    private CartItemDtosMap visits;

    private CartItemDtosMap deliveries;

    private CartItemDtosMap pickups;

    @Builder
    @Getter
    public static class PaymentInfoDto {

        private Integer totalItemAmount;

        private Integer totalShippingFee;

        private Integer totalPaymentAmount;
    }

    public static class CartItemDtosMap extends HashMap<Shop, CartItemDtosByShop> {
        public void add(Shop key, CartItemDto value) {
            if (!super.containsKey(key)) {
                super.put(key, CartItemDtosByShop.builder().amount(0).cartItemDtos(new ArrayList<>()).build());
            }

            super.get(key).add(value);
        }
    }

    @Builder
    @Getter
    public static class CartItemDtosByShop {

        private Integer amount;

        private List<CartItemDto> cartItemDtos;

        public void add(CartItemDto cartItemDto) {

            this.amount += cartItemDto.getAmount();
            this.cartItemDtos.add(cartItemDto);
        }
    }

    @Builder
    @Getter
    public static class CartItemDto {

        private CartItem cartItem;

        private Integer amount;

        private Integer shippingFee;

        private List<SelectedOptionDto> selectedOptionDtos;

        @Builder
        @Getter
        public static class SelectedOptionDto {

            private SelectedOption selectedOption;

            private Integer amount;
        }
    }
}
