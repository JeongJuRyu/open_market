package com.tmax.cm.superstore.order.controller.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.order.controller.dto.GetVisitAndPickupOrderSelectedOptionAllByShopDto.Response;
import com.tmax.cm.superstore.order.controller.dto.GetVisitAndPickupOrderSelectedOptionAllByShopDto.Response.GetSelectedOptionDto;
import com.tmax.cm.superstore.order.controller.dto.GetVisitAndPickupOrderSelectedOptionAllByShopDto.Response.GetSelectedOptionDto.GetOrderOptionGroupDto;
import com.tmax.cm.superstore.order.controller.dto.GetVisitAndPickupOrderSelectedOptionAllByShopDto.Response.GetSelectedOptionDto.GetOrderOptionGroupDto.GetOrderOptionDto;
import com.tmax.cm.superstore.order.entity.OrderOption;
import com.tmax.cm.superstore.order.entity.OrderOptionGroup;
import com.tmax.cm.superstore.order.entity.PickupOrder;
import com.tmax.cm.superstore.order.entity.PickupOrderItem;
import com.tmax.cm.superstore.order.entity.PickupOrderSelectedOption;
import com.tmax.cm.superstore.order.entity.VisitOrder;

@Mapper(config = CommonMapperConfig.class)
public interface GetPickupOrderSelectedOptionAllByShopDtoMapper {

    default Response toResponse(List<VisitOrder> visitOrders, List<PickupOrder> pickupOrders) {
        List<GetSelectedOptionDto> getSelectedOptionDtos = new ArrayList<>();

        for (VisitOrder visitOrder : visitOrders) {
            for (PickupOrderItem pickupOrderItem : visitOrder.getPickupOrderItems()) {
                for (PickupOrderSelectedOption pickupOrderSelectedOption : pickupOrderItem
                        .getPickupOrderSelectedOptions()) {

                    getSelectedOptionDtos.add(this.toGetSelectedOptionDto(pickupOrderSelectedOption,
                            pickupOrderItem.getItem().getId(), pickupOrderItem.getName(), pickupOrderItem.getPrice()));
                }
            }
        }

        for (PickupOrder pickupOrder : pickupOrders) {
            for (PickupOrderItem pickupOrderItem : pickupOrder.getPickupOrderItems()) {
                for (PickupOrderSelectedOption pickupOrderSelectedOption : pickupOrderItem
                        .getPickupOrderSelectedOptions()) {

                    getSelectedOptionDtos.add(this.toGetSelectedOptionDto(pickupOrderSelectedOption,
                            pickupOrderItem.getItem().getId(), pickupOrderItem.getName(), pickupOrderItem.getPrice()));
                }
            }
        }

        return Response.builder().orderSelectedOptions(getSelectedOptionDtos).build();
    }

    @Mapping(target = "orderSelectedOptionId", source = "pickupOrderSelectedOption.id")
    @Mapping(target = ".", source = "pickupOrderSelectedOption.pickup")
    GetSelectedOptionDto toGetSelectedOptionDto(PickupOrderSelectedOption pickupOrderSelectedOption, UUID itemId,
            String itemName, Integer itemPrice);

    @Mapping(target = "optionGroupName", source = "name")
    GetOrderOptionGroupDto toGetOrderOptionDto(OrderOptionGroup orderOptionGroup);

    @Mapping(target = "optionName", source = "name")
    GetOrderOptionDto toGetOrderOptionDto(OrderOption orderOption);
}
