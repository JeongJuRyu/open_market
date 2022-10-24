package com.tmax.cm.superstore.order.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.order.controller.dto.GetShippingAndDeliveryOrderSelectedOptionByUserDto.Response;
import com.tmax.cm.superstore.order.controller.dto.GetShippingAndDeliveryOrderSelectedOptionByUserDto.Response.GetOrderOptionGroupDto;
import com.tmax.cm.superstore.order.controller.dto.GetShippingAndDeliveryOrderSelectedOptionByUserDto.Response.GetOrderOptionGroupDto.GetOrderOptionDto;
import com.tmax.cm.superstore.order.entity.OrderOption;
import com.tmax.cm.superstore.order.entity.OrderOptionGroup;
import com.tmax.cm.superstore.order.service.dto.ReadShippingOrderSelectedOptionDto;

@Mapper(config = CommonMapperConfig.class)
public interface GetShippingAndDeliveryOrderSelectedOptionByUserDtoMapper {

    @Mapping(target = ".", source = "readShippingOrderSelectedOptionDto")
    @Mapping(target = ".", source = "readShippingOrderSelectedOptionDto.order.payment")
    @Mapping(target = ".", source = "readShippingOrderSelectedOptionDto.shippingOrderItem")
    @Mapping(target = "itemId", source = "readShippingOrderSelectedOptionDto.shippingOrderItem.item.id")
    @Mapping(target = "itemName", source = "readShippingOrderSelectedOptionDto.shippingOrderItem.name")
    @Mapping(target = "itemPrice", source = "readShippingOrderSelectedOptionDto.shippingOrderItem.price")
    @Mapping(target = "shippingFee", source = "readShippingOrderSelectedOptionDto.shippingOrderItem.shippingFee.price")
    @Mapping(target = "amount", source = "readShippingOrderSelectedOptionDto.shippingOrderSelectedOption.amount")
    @Mapping(target = "createdAt", source = "readShippingOrderSelectedOptionDto.shippingOrderSelectedOption.createdAt")
    @Mapping(target = "orderSelectedOptionId", source = "readShippingOrderSelectedOptionDto.shippingOrderSelectedOption.id")
    @Mapping(target = ".", source = "readShippingOrderSelectedOptionDto.shippingOrderSelectedOption")
    @Mapping(target = ".", source = "readShippingOrderSelectedOptionDto.shippingOrderSelectedOption.seller")
    @Mapping(target = ".", source = "readShippingOrderSelectedOptionDto.shippingOrderSelectedOption.shipping")
    @Mapping(target = "address", source = "readShippingOrderSelectedOptionDto.shippingOrderSelectedOption.shipping.address")
    Response toResponse(ReadShippingOrderSelectedOptionDto readShippingOrderSelectedOptionDto);

    @Mapping(target = "optionGroupName", source = "name")
    GetOrderOptionGroupDto toGetOrderOptionDto(OrderOptionGroup orderOptionGroup);

    @Mapping(target = "optionName", source = "name")
    GetOrderOptionDto toGetOrderOptionDto(OrderOption orderOption);
}
