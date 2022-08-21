package com.tmax.cm.superstore.code;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CartType implements Code {
    SHIPPING_VISIT("SHIPPING_VISIT", "배송/방문수령", "shipping/visit"),
    DELIVERY_PICKUP("DELIVERY_PICKUP", "배달/픽업", "delivery/pickup"),
    RESERVATION("RESERVATION", "예약", "reservation");

    private String code;
    private String description;

    @JsonValue
    private String jsonValue;
}
