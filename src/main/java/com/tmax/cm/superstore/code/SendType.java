package com.tmax.cm.superstore.code;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SendType implements Code {
    SHIPPING("SHIPPING", "배송", "shipping"),
    VISIT("VISIT", "방문수령", "visit"),

    DELIVERY("DELIVERY", "배달", "delivery"),
    PICKUP("PICKUP", "픽업", "pickup"),

    RESERVATION("RESERVATION", "예약", "reservation");

    private String code;
    private String description;

    @JsonValue
    private String jsonValue;
}
