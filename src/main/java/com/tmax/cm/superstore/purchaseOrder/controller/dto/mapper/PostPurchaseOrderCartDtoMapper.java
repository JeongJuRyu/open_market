package com.tmax.cm.superstore.purchaseOrder.controller.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tmax.cm.superstore.cart.entity.CartOption;
import com.tmax.cm.superstore.cart.entity.CartOptionGroup;
import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.purchaseOrder.controller.dto.PostPurchaseOrderCartDto;
import com.tmax.cm.superstore.purchaseOrder.service.dto.PurchaseOrderDto;
import com.tmax.cm.superstore.purchaseOrder.service.dto.PurchaseOrderDto.CartItemDtosByShop;
import com.tmax.cm.superstore.shop.entity.Shop;

@Mapper(config = CommonMapperConfig.class)
public interface PostPurchaseOrderCartDtoMapper {

    PostPurchaseOrderCartDto.Response toResponse(PurchaseOrderDto purchaseOrderDto);

    default List<PostPurchaseOrderCartDto.Response.ShippingDto> toShippingDtos(
            PurchaseOrderDto.CartItemDtosMap cartItemDtosMap) {

        List<PostPurchaseOrderCartDto.Response.ShippingDto> shippingDtos = new ArrayList<>();

        for (Entry<Shop, CartItemDtosByShop> entry : cartItemDtosMap.entrySet()) {

            shippingDtos.add(PostPurchaseOrderCartDto.Response.ShippingDto.builder()
                    .shopId(entry.getKey().getId())
                    .shopName(entry.getKey().getName())
                    .cartItemAmount(entry.getValue().getAmount())
                    .cartItems(this.toCartItemDtos(entry.getValue().getCartItemDtos()))
                    .build());
        }

        return shippingDtos;
    }

    default List<PostPurchaseOrderCartDto.Response.VisitDto> toVisitDtos(
            PurchaseOrderDto.CartItemDtosMap cartItemDtosMap) {

        List<PostPurchaseOrderCartDto.Response.VisitDto> visitDtos = new ArrayList<>();

        for (Entry<Shop, CartItemDtosByShop> entry : cartItemDtosMap.entrySet()) {

            visitDtos.add(PostPurchaseOrderCartDto.Response.VisitDto.builder()
                    .shopId(entry.getKey().getId())
                    .shopName(entry.getKey().getName())
                    .shopAddress(entry.getKey().getAddress())
                    .cartItemAmount(entry.getValue().getAmount())
                    .cartItems(this.toCartItemDtos(entry.getValue().getCartItemDtos()))
                    .build());
        }

        return visitDtos;
    }

    List<PostPurchaseOrderCartDto.Response.CartItemDto> toCartItemDtos(List<PurchaseOrderDto.CartItemDto> cartItemDtos);

    @Mapping(target = "cartItemId", source = "cartItem.id")
    @Mapping(target = "itemId", source = "cartItem.item.id")
    @Mapping(target = "itemName", source = "cartItem.item.name")
    @Mapping(target = "itemThumbnailURL", ignore = true)
    @Mapping(target = "cartItemAmount", source = "amount")
    @Mapping(target = "selectedOptions", source = "selectedOptionDtos")
    PostPurchaseOrderCartDto.Response.CartItemDto toCartItemDto(PurchaseOrderDto.CartItemDto cartItemDto);

    @Mapping(target = "selectedOptionAmount", source = "amount")
    @Mapping(target = "selectedOptionCount", source = "selectedOption.count")
    @Mapping(target = "cartOptionGroups", source = "selectedOption.cartOptionGroups")
    PostPurchaseOrderCartDto.Response.CartItemDto.SelectedOptionDto toSelectedOptionDto(
            PurchaseOrderDto.CartItemDto.SelectedOptionDto selectedOptionDto);

    @Mapping(target = "optionGroupName", source = "optionGroup.name")
    @Mapping(target = ".", source = "optionGroup")
    PostPurchaseOrderCartDto.Response.CartItemDto.SelectedOptionDto.CartOptionGroupDto toCartOptionGroupDto(
            CartOptionGroup cartOptionGroup);

    @Mapping(target = "optionName", source = "option.name")
    @Mapping(target = "optionPrice", source = "option.price")
    @Mapping(target = "cartOptionCount", source = "count")
    PostPurchaseOrderCartDto.Response.CartItemDto.SelectedOptionDto.CartOptionGroupDto.CartOptionDto toCartOptionDto(
            CartOption cartOption);
}
