package com.tmax.cm.superstore.order.controller.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tmax.cm.superstore.code.PaymentType;
import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.order.controller.dto.GetVisitAndPickupOrderSelectedOptionAllByUserDto.Response;
import com.tmax.cm.superstore.order.controller.dto.GetVisitAndPickupOrderSelectedOptionAllByUserDto.Response.GetSelectedOptionDto;
import com.tmax.cm.superstore.order.controller.dto.GetVisitAndPickupOrderSelectedOptionAllByUserDto.Response.GetSelectedOptionDto.GetOrderOptionGroupDto;
import com.tmax.cm.superstore.order.controller.dto.GetVisitAndPickupOrderSelectedOptionAllByUserDto.Response.GetSelectedOptionDto.GetOrderOptionGroupDto.GetOrderOptionDto;
import com.tmax.cm.superstore.order.entity.OrderOption;
import com.tmax.cm.superstore.order.entity.OrderOptionGroup;
import com.tmax.cm.superstore.order.entity.PickupOrder;
import com.tmax.cm.superstore.order.entity.PickupOrderItem;
import com.tmax.cm.superstore.order.entity.PickupOrderSelectedOption;
import com.tmax.cm.superstore.order.entity.VisitOrder;
import com.tmax.cm.superstore.seller.entity.Seller;

@Mapper(config = CommonMapperConfig.class)
public interface GetPickupOrderSelectedOptionAllByUserDtoMapper {

    default Response toResponse(List<VisitOrder> visitOrders, List<PickupOrder> pickupOrders, PaymentType paymentType) {
        List<GetSelectedOptionDto> getSelectedOptionDtos = new ArrayList<>();

        boolean isReviewExist;

        for (VisitOrder visitOrder : visitOrders) {
            for (PickupOrderItem pickupOrderItem : visitOrder.getPickupOrderItems()) {

                if (pickupOrderItem.getItem().getReviews().isEmpty()) {
                    isReviewExist = false;
                } else {
                    isReviewExist = true;
                }

                for (PickupOrderSelectedOption pickupOrderSelectedOption : pickupOrderItem
                        .getPickupOrderSelectedOptions()) {

                    getSelectedOptionDtos.add(this.toGetSelectedOptionDto(pickupOrderSelectedOption,
                            pickupOrderItem.getItem().getId(), pickupOrderItem.getName(), pickupOrderItem.getPrice(),
                            pickupOrderSelectedOption.getSeller(),
                            "http://192.168.159.42:8888/images/58c04a256d774a1a8d6c8f3659eeadbf", isReviewExist,
                            paymentType));
                }
            }
        }

        for (PickupOrder pickupOrder : pickupOrders) {
            for (PickupOrderItem pickupOrderItem : pickupOrder.getPickupOrderItems()) {
                if (pickupOrderItem.getItem().getReviews().isEmpty()) {
                    isReviewExist = false;
                } else {
                    isReviewExist = true;
                }

                for (PickupOrderSelectedOption pickupOrderSelectedOption : pickupOrderItem
                        .getPickupOrderSelectedOptions()) {

                    getSelectedOptionDtos.add(this.toGetSelectedOptionDto(pickupOrderSelectedOption,
                            pickupOrderItem.getItem().getId(), pickupOrderItem.getName(), pickupOrderItem.getPrice(),
                            pickupOrderSelectedOption.getSeller(),
                            "http://192.168.159.42:8888/images/58c04a256d774a1a8d6c8f3659eeadbf", isReviewExist,
                            paymentType));
                }
            }
        }

        return Response.builder().orderSelectedOptions(getSelectedOptionDtos).build();
    }

    @Mapping(target = "orderSelectedOptionId", source = "pickupOrderSelectedOption.id")
    @Mapping(target = ".", source = "pickupOrderSelectedOption.pickup")
    @Mapping(target = ".", source = "seller")
    GetSelectedOptionDto toGetSelectedOptionDto(PickupOrderSelectedOption pickupOrderSelectedOption, UUID itemId,
            String itemName, Integer itemPrice, Seller seller, String itemImage, boolean isReviewExist,
            PaymentType paymentType);

    @Mapping(target = "optionGroupName", source = "name")
    GetOrderOptionGroupDto toGetOrderOptionDto(OrderOptionGroup orderOptionGroup);

    @Mapping(target = "optionName", source = "name")
    GetOrderOptionDto toGetOrderOptionDto(OrderOption orderOption);
}
