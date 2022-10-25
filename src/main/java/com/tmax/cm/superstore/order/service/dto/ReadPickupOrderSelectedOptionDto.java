package com.tmax.cm.superstore.order.service.dto;

import com.tmax.cm.superstore.code.SendType;
import com.tmax.cm.superstore.order.entity.Order;
import com.tmax.cm.superstore.order.entity.PickupOrderItem;
import com.tmax.cm.superstore.order.entity.PickupOrderSelectedOption;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReadPickupOrderSelectedOptionDto {

    Order order;

    PickupOrderItem pickupOrderItem;

    PickupOrderSelectedOption pickupOrderSelectedOption;

    @Builder.Default
    String itemImage = "http://192.168.159.42:8888/images/58c04a256d774a1a8d6c8f3659eeadbf";

    Boolean isReviewExist;

    SendType sendType;
}
