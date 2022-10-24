package com.tmax.cm.superstore.order.service.dto;

import com.tmax.cm.superstore.order.entity.Order;
import com.tmax.cm.superstore.order.entity.ShippingOrderItem;
import com.tmax.cm.superstore.order.entity.ShippingOrderSelectedOption;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReadShippingOrderSelectedOptionDto {

    Order order;

    ShippingOrderItem shippingOrderItem;

    ShippingOrderSelectedOption shippingOrderSelectedOption;

    @Builder.Default
    String itemImage = "http://192.168.159.42:8888/images/58c04a256d774a1a8d6c8f3659eeadbf";

    Boolean isReviewExist;
}
