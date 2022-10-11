package com.tmax.cm.superstore.purchaseOrder.service.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;

import com.tmax.cm.superstore.cart.entity.CartItem;
import com.tmax.cm.superstore.cart.entity.CartOption;
import com.tmax.cm.superstore.cart.entity.CartOptionGroup;
import com.tmax.cm.superstore.cart.entity.SelectedOption;
import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.purchaseOrder.service.dto.PurchaseOrderDto.CartItemDto;
import com.tmax.cm.superstore.purchaseOrder.service.dto.PurchaseOrderDto.CartItemDto.SelectedOptionDto;

@Mapper(config = CommonMapperConfig.class)
public interface PurchaseOrderDtoMapper {

    default CartItemDto toDto(CartItem cartItem, Integer shippingFee) {

        Integer selectedOptionCount;
        Integer selectedOptionAmount;
        Integer cartItemAmount = 0;
        Integer cartOptionGroupAmount;

        Item item = cartItem.getItem();

        List<SelectedOptionDto> selectedOptionDtos = new ArrayList<>();

        for (SelectedOption selectedOption : cartItem.getSelectedOptions()) {
            selectedOptionCount = selectedOption.getCount();
            selectedOptionAmount = item.getPrice();

            for (CartOptionGroup cartOptionGroup : selectedOption.getCartOptionGroups()) {
                cartOptionGroupAmount = 0;

                for (CartOption cartOption : cartOptionGroup.getCartOptions()) {
                    cartOptionGroupAmount += cartOption.getOption().getPrice() * cartOption.getCount();
                }

                selectedOptionAmount += cartOptionGroupAmount;
            }

            SelectedOptionDto selectedOptionDto = SelectedOptionDto.builder()
                    .amount(selectedOptionAmount)
                    .selectedOption(selectedOption)
                    .build();

            selectedOptionDtos.add(selectedOptionDto);

            cartItemAmount += selectedOptionCount * selectedOptionAmount;
        }

        CartItemDto cartItemDto = CartItemDto.builder()
                .cartItem(cartItem)
                .amount(cartItemAmount)
                .shippingFee(shippingFee)
                .selectedOptionDtos(selectedOptionDtos)
                .build();

        return cartItemDto;
    }
}