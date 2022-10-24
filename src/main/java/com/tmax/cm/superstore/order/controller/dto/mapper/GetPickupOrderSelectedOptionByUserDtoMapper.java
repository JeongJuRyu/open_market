package com.tmax.cm.superstore.order.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.order.controller.dto.GetVisitAndPickupOrderSelectedOptionByUserDto.Response;
import com.tmax.cm.superstore.order.controller.dto.GetVisitAndPickupOrderSelectedOptionByUserDto.Response.GetOrderOptionGroupDto;
import com.tmax.cm.superstore.order.controller.dto.GetVisitAndPickupOrderSelectedOptionByUserDto.Response.GetOrderOptionGroupDto.GetOrderOptionDto;
import com.tmax.cm.superstore.order.entity.OrderOption;
import com.tmax.cm.superstore.order.entity.OrderOptionGroup;
import com.tmax.cm.superstore.order.service.dto.ReadPickupOrderSelectedOptionDto;

@Mapper(config = CommonMapperConfig.class)
public interface GetPickupOrderSelectedOptionByUserDtoMapper {

    @Mapping(target = ".", source = "readPickupOrderSelectedOptionDto")
    @Mapping(target = ".", source = "readPickupOrderSelectedOptionDto.order.payment")
    @Mapping(target = ".", source = "readPickupOrderSelectedOptionDto.pickupOrderItem")
    @Mapping(target = "itemId", source = "readPickupOrderSelectedOptionDto.pickupOrderItem.item.id")
    @Mapping(target = "itemName", source = "readPickupOrderSelectedOptionDto.pickupOrderItem.name")
    @Mapping(target = "itemPrice", source = "readPickupOrderSelectedOptionDto.pickupOrderItem.price")
    @Mapping(target = "amount", source = "readPickupOrderSelectedOptionDto.pickupOrderSelectedOption.amount")
    @Mapping(target = "createdAt", source = "readPickupOrderSelectedOptionDto.pickupOrderSelectedOption.createdAt")
    @Mapping(target = "orderSelectedOptionId", source = "readPickupOrderSelectedOptionDto.pickupOrderSelectedOption.id")
    @Mapping(target = ".", source = "readPickupOrderSelectedOptionDto.pickupOrderSelectedOption")
    @Mapping(target = ".", source = "readPickupOrderSelectedOptionDto.pickupOrderSelectedOption.seller")
    @Mapping(target = ".", source = "readPickupOrderSelectedOptionDto.pickupOrderSelectedOption.pickup")
    @Mapping(target = "address", source = "readPickupOrderSelectedOptionDto.pickupOrderSelectedOption.pickup.address")
    Response toResponse(ReadPickupOrderSelectedOptionDto readPickupOrderSelectedOptionDto);

    @Mapping(target = "optionGroupName", source = "name")
    GetOrderOptionGroupDto toGetOrderOptionDto(OrderOptionGroup orderOptionGroup);

    @Mapping(target = "optionName", source = "name")
    GetOrderOptionDto toGetOrderOptionDto(OrderOption orderOption);
}
