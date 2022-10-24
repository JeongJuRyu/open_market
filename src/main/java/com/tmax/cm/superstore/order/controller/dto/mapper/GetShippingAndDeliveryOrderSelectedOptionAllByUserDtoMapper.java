package com.tmax.cm.superstore.order.controller.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tmax.cm.superstore.code.PaymentType;
import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.order.controller.dto.GetShippingAndDeliveryOrderSelectedOptionAllByUserDto.Response;
import com.tmax.cm.superstore.order.controller.dto.GetShippingAndDeliveryOrderSelectedOptionAllByUserDto.Response.GetSelectedOptionDto;
import com.tmax.cm.superstore.order.controller.dto.GetShippingAndDeliveryOrderSelectedOptionAllByUserDto.Response.GetSelectedOptionDto.GetOrderOptionGroupDto;
import com.tmax.cm.superstore.order.controller.dto.GetShippingAndDeliveryOrderSelectedOptionAllByUserDto.Response.GetSelectedOptionDto.GetOrderOptionGroupDto.GetOrderOptionDto;
import com.tmax.cm.superstore.order.entity.DeliveryOrder;
import com.tmax.cm.superstore.order.entity.OrderOption;
import com.tmax.cm.superstore.order.entity.OrderOptionGroup;
import com.tmax.cm.superstore.order.entity.ShippingOrder;
import com.tmax.cm.superstore.order.entity.ShippingOrderItem;
import com.tmax.cm.superstore.order.entity.ShippingOrderSelectedOption;
import com.tmax.cm.superstore.seller.entity.Seller;

@Mapper(config = CommonMapperConfig.class)
public interface GetShippingAndDeliveryOrderSelectedOptionAllByUserDtoMapper {

    default Response toResponse(List<ShippingOrder> shippingOrders, List<DeliveryOrder> deliveryOrders,
            PaymentType paymentType) {
        List<GetSelectedOptionDto> getSelectedOptionDtos = new ArrayList<>();

        boolean isReviewExist;

        for (ShippingOrder shippingOrder : shippingOrders) {
            for (ShippingOrderItem shippingOrderItem : shippingOrder.getShippingOrderItems()) {

                if (shippingOrderItem.getItem().getReviews().isEmpty()) {
                    isReviewExist = false;
                } else {
                    isReviewExist = true;
                }

                for (ShippingOrderSelectedOption shippingOrderSelectedOption : shippingOrderItem
                        .getShippingOrderSelectedOptions()) {

                    getSelectedOptionDtos.add(this.toGetSelectedOptionDto(shippingOrderSelectedOption,
                            shippingOrderItem.getItem().getId(), shippingOrderItem.getName(),
                            shippingOrderItem.getPrice(),
                            shippingOrderSelectedOption.getSeller(),
                            "http://192.168.159.42:8888/images/58c04a256d774a1a8d6c8f3659eeadbf", isReviewExist,
                            paymentType));
                }
            }
        }

        for (DeliveryOrder deliveryOrder : deliveryOrders) {
            for (ShippingOrderItem shippingOrderItem : deliveryOrder.getShippingOrderItems()) {

                if (shippingOrderItem.getItem().getReviews().isEmpty()) {
                    isReviewExist = false;
                } else {
                    isReviewExist = true;
                }

                for (ShippingOrderSelectedOption shippingOrderSelectedOption : shippingOrderItem
                        .getShippingOrderSelectedOptions()) {

                    getSelectedOptionDtos.add(this.toGetSelectedOptionDto(shippingOrderSelectedOption,
                            shippingOrderItem.getItem().getId(), shippingOrderItem.getName(),
                            shippingOrderItem.getPrice(),
                            shippingOrderSelectedOption.getSeller(),
                            "http://192.168.159.42:8888/images/58c04a256d774a1a8d6c8f3659eeadbf", isReviewExist,
                            paymentType));
                }
            }
        }

        return Response.builder().orderSelectedOptions(getSelectedOptionDtos).build();
    }

    @Mapping(target = "orderSelectedOptionId", source = "shippingOrderSelectedOption.id")
    @Mapping(target = ".", source = "shippingOrderSelectedOption.shipping")
    GetSelectedOptionDto toGetSelectedOptionDto(ShippingOrderSelectedOption shippingOrderSelectedOption, UUID itemId,
            String itemName, Integer itemPrice, Seller seller, String itemImage, boolean isReviewExist,
            PaymentType paymentType);

    @Mapping(target = "optionGroupName", source = "name")
    GetOrderOptionGroupDto toGetOrderOptionDto(OrderOptionGroup orderOptionGroup);

    @Mapping(target = "optionName", source = "name")
    GetOrderOptionDto toGetOrderOptionDto(OrderOption orderOption);
}
