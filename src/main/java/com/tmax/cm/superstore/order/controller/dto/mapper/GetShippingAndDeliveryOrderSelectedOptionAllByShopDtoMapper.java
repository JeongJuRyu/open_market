package com.tmax.cm.superstore.order.controller.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tmax.cm.superstore.code.SendType;
import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.order.controller.dto.GetShippingAndDeliveryOrderSelectedOptionAllByShopDto.Response;
import com.tmax.cm.superstore.order.controller.dto.GetShippingAndDeliveryOrderSelectedOptionAllByShopDto.Response.GetSelectedOptionDto;
import com.tmax.cm.superstore.order.controller.dto.GetShippingAndDeliveryOrderSelectedOptionAllByShopDto.Response.GetSelectedOptionDto.GetOrderOptionGroupDto;
import com.tmax.cm.superstore.order.controller.dto.GetShippingAndDeliveryOrderSelectedOptionAllByShopDto.Response.GetSelectedOptionDto.GetOrderOptionGroupDto.GetOrderOptionDto;
import com.tmax.cm.superstore.order.entity.DeliveryOrder;
import com.tmax.cm.superstore.order.entity.OrderOption;
import com.tmax.cm.superstore.order.entity.OrderOptionGroup;
import com.tmax.cm.superstore.order.entity.ShippingOrder;
import com.tmax.cm.superstore.order.entity.ShippingOrderItem;
import com.tmax.cm.superstore.order.entity.ShippingOrderSelectedOption;

@Mapper(config = CommonMapperConfig.class)
public interface GetShippingAndDeliveryOrderSelectedOptionAllByShopDtoMapper {

    default Response toResponse(List<ShippingOrder> shippingOrders, List<DeliveryOrder> deliveryOrders) {
        List<GetSelectedOptionDto> getSelectedOptionDtos = new ArrayList<>();

        for (ShippingOrder shippingOrder : shippingOrders) {
            for (ShippingOrderItem shippingOrderItem : shippingOrder.getShippingOrderItems()) {
                for (ShippingOrderSelectedOption shippingOrderSelectedOption : shippingOrderItem
                        .getShippingOrderSelectedOptions()) {

                    getSelectedOptionDtos.add(this.toGetSelectedOptionDto(shippingOrderSelectedOption,
                            shippingOrderItem.getItem().getId(), shippingOrderItem.getName(),
                            shippingOrderItem.getPrice(), SendType.SHIPPING,
                            shippingOrderItem.getShippingFee().getPrice()));
                }
            }
        }

        for (DeliveryOrder deliveryOrder : deliveryOrders) {
            for (ShippingOrderItem shippingOrderItem : deliveryOrder.getShippingOrderItems()) {
                for (ShippingOrderSelectedOption shippingOrderSelectedOption : shippingOrderItem
                        .getShippingOrderSelectedOptions()) {

                    getSelectedOptionDtos.add(this.toGetSelectedOptionDto(shippingOrderSelectedOption,
                            shippingOrderItem.getItem().getId(), shippingOrderItem.getName(),
                            shippingOrderItem.getPrice(), SendType.DELIVERY,
                            shippingOrderItem.getShippingFee().getPrice()));
                }
            }
        }

        return Response.builder().orderSelectedOptions(getSelectedOptionDtos).build();
    }

    @Mapping(target = "orderSelectedOptionId", source = "shippingOrderSelectedOption.id")
    @Mapping(target = ".", source = "shippingOrderSelectedOption.shipping")
    GetSelectedOptionDto toGetSelectedOptionDto(ShippingOrderSelectedOption shippingOrderSelectedOption, UUID itemId,
            String itemName, Integer itemPrice, SendType sendType, Integer shippingFee);

    @Mapping(target = "optionGroupName", source = "name")
    GetOrderOptionGroupDto toGetOrderOptionDto(OrderOptionGroup orderOptionGroup);

    @Mapping(target = "optionName", source = "name")
    GetOrderOptionDto toGetOrderOptionDto(OrderOption orderOption);
}
