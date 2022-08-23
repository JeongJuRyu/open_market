package com.tmax.cm.superstore.cart.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tmax.cm.superstore.cart.dto.GetCartReservationItemDto;
import com.tmax.cm.superstore.cart.entity.CartItem;
import com.tmax.cm.superstore.cart.entity.CartOption;
import com.tmax.cm.superstore.cart.entity.CartOptionGroup;
import com.tmax.cm.superstore.cart.entity.SelectedOption;
import com.tmax.cm.superstore.config.CommonMapperConfig;

@Mapper(config = CommonMapperConfig.class)
public interface GetCartReservationItemDtoMapper {
    
    @Mapping(target = "shopId", source = "item.shop.id")
    @Mapping(target = "shopName", source = "item.shop.name")
    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "itemName", source = "item.name")
    @Mapping(target = "itemThumbnailURL", ignore = true)
    @Mapping(target = "itemPrice", source = "item.price")
    @Mapping(target = "cartItemId", source = "id")
    @Mapping(target = ".", source = "cartReservationItem")
    @Mapping(target = "cartItemAmount", ignore = true) // TODO
    @Mapping(target = "cartItemCount", ignore = true) // TODO
    GetCartReservationItemDto.Response toResponse(CartItem cartItem);

    @Mapping(target = "selectedOptionId", source = "id")
    @Mapping(target = "selectedOptionCount", source = "count")
    @Mapping(target = "selectedOptionAmount", ignore = true) // TODO
    GetCartReservationItemDto.Response.GetSelectedOptionDto toGetSelectedOptionDto(SelectedOption selectedOption);

    @Mapping(target = "cartOptionGroupId", source = "id")
    @Mapping(target = "optionGroupId", source = "optionGroup.id")
    @Mapping(target = "optionGroupName", source = "optionGroup.name")
    @Mapping(target = "isNecessary", source = "optionGroup.isNecessary")
    GetCartReservationItemDto.Response.GetSelectedOptionDto.GetCartOptionGroupDto toGetCartOptionGroupDto(
            CartOptionGroup cartOptionGroup);

    @Mapping(target = "cartOptionId", source = "id")
    @Mapping(target = "optionId", source = "option.id")
    @Mapping(target = "optionName", source = "option.name")
    @Mapping(target = "optionPrice", source = "option.price")
    @Mapping(target = "cartItemOptionCount", source = "count")
    GetCartReservationItemDto.Response.GetSelectedOptionDto.GetCartOptionGroupDto.GetCartOptionDto toGetCartOptionDto(
            CartOption cartOption);
}
