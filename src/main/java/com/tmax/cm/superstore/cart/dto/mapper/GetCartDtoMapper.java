package com.tmax.cm.superstore.cart.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tmax.cm.superstore.cart.dto.GetCartDto;
import com.tmax.cm.superstore.cart.entity.CartItem;
import com.tmax.cm.superstore.cart.entity.CartOption;
import com.tmax.cm.superstore.cart.entity.CartOptionGroup;
import com.tmax.cm.superstore.cart.entity.SelectedOption;
import com.tmax.cm.superstore.config.CommonMapperConfig;

@Mapper(config = CommonMapperConfig.class)
public interface GetCartDtoMapper {

        @Mapping(target = "shippingVisit", source = "shippingVisit")
        @Mapping(target = "deliveryPickup", source = "deliveryPickup")
        @Mapping(target = "reservation", source = "reservation")
        GetCartDto.Response toResponse(List<CartItem> shippingVisit, List<CartItem> deliveryPickup,
                        List<CartItem> reservation);

        @Mapping(target = "shopId", source = "item.seller.sellerId")
        @Mapping(target = "shopName", source = "item.seller.sellerName")
        @Mapping(target = "itemId", source = "item.id")
        @Mapping(target = "itemName", source = "item.name")
        @Mapping(target = "itemThumbnailURL", ignore = true)
        @Mapping(target = "itemPrice", source = "item.price")
        @Mapping(target = "cartItemId", source = "id")
        @Mapping(target = "cartItemAmount", ignore = true)
        @Mapping(target = "cartItemCount", ignore = true)
        GetCartDto.Response.GetCartItemDto toCartItemDto(CartItem cartItem);

        @Mapping(target = "selectedOptionId", source = "id")
        @Mapping(target = "selectedOptionCount", source = "count")
        @Mapping(target = "selectedOptionAmount", ignore = true)
        GetCartDto.Response.GetCartItemDto.GetSelectedOptionDto toGetSelectedOptionDto(SelectedOption selectedOption);

        @Mapping(target = "cartOptionGroupId", source = "id")
        @Mapping(target = "optionGroupId", source = "optionGroup.id")
        @Mapping(target = "optionGroupName", source = "optionGroup.name")
        @Mapping(target = "isNecessary", source = "optionGroup.isNecessary")
        GetCartDto.Response.GetCartItemDto.GetSelectedOptionDto.GetCartOptionGroupDto toGetCartOptionGroupDto(
                        CartOptionGroup cartOptionGroup);

        @Mapping(target = "cartOptionId", source = "id")
        @Mapping(target = "optionId", source = "option.id")
        @Mapping(target = "optionName", source = "option.name")
        @Mapping(target = "optionPrice", source = "option.price")
        @Mapping(target = "cartItemOptionCount", source = "count")
        GetCartDto.Response.GetCartItemDto.GetSelectedOptionDto.GetCartOptionGroupDto.GetCartOptionDto toGetCartOptionDto(
                        CartOption cartOption);

        @Mapping(target = "shopId", source = "item.seller.sellerId")
        @Mapping(target = "shopName", source = "item.seller.sellerName")
        @Mapping(target = "itemId", source = "item.id")
        @Mapping(target = "itemName", source = "item.name")
        @Mapping(target = "itemThumbnailURL", ignore = true)
        @Mapping(target = "itemPrice", source = "item.price")
        @Mapping(target = "cartItemId", source = "id")
        @Mapping(target = ".", source = "cartReservationItem")
        @Mapping(target = "cartItemAmount", ignore = true)
        @Mapping(target = "cartItemCount", ignore = true)
        GetCartDto.Response.GetCartReservationItemDto toCartReservationItemDto(CartItem cartItem);

        @Mapping(target = "selectedOptionId", source = "id")
        @Mapping(target = "selectedOptionCount", source = "count")
        @Mapping(target = "selectedOptionAmount", ignore = true)
        GetCartDto.Response.GetCartReservationItemDto.GetSelectedOptionDto toCartReservationItemGetSelectedOptionDto(
                        SelectedOption selectedOption);

        @Mapping(target = "cartOptionGroupId", source = "id")
        @Mapping(target = "optionGroupId", source = "optionGroup.id")
        @Mapping(target = "optionGroupName", source = "optionGroup.name")
        @Mapping(target = "isNecessary", source = "optionGroup.isNecessary")
        GetCartDto.Response.GetCartReservationItemDto.GetSelectedOptionDto.GetCartOptionGroupDto toCartReservationItemGetCartOptionGroupDto(
                        CartOptionGroup cartOptionGroup);

        @Mapping(target = "cartOptionId", source = "id")
        @Mapping(target = "optionId", source = "option.id")
        @Mapping(target = "optionName", source = "option.name")
        @Mapping(target = "optionPrice", source = "option.price")
        @Mapping(target = "cartItemOptionCount", source = "count")
        GetCartDto.Response.GetCartReservationItemDto.GetSelectedOptionDto.GetCartOptionGroupDto.GetCartOptionDto toCartReservationItemGetCartOptionDto(
                        CartOption cartOption);
}
