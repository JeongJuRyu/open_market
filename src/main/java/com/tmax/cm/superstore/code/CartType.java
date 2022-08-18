package com.tmax.cm.superstore.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CartType implements Code {
    SHIPPING_VISIT("SHIPPING_VISIT", "배송/방문수령"),
    DELIVERY_PICKUP("DELIVERY_PICKUP", "배달/픽업"),
    RESERVATION("RESERVATION", "예약");

    private String code;
    private String description;
}
