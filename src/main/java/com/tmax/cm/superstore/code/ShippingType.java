package com.tmax.cm.superstore.code;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ShippingType implements Code {

    SHIPPING_WAITING("SHIPPING_WAITING", "배송 요청 대기", "shipping/waiting"),
    SHIPPING_ACCEPT("SHIPPING_ACCEPT", "배송 요청 수락 및 배송 중", "shipping/accepted"),
    SHIPPING_DONE("SHIPPING_DONE", "배송 완료", "shipping/done"),
    SHIPPING_REFUSE("SHIPPING_REFUSE", "배송 요청 거절", "shipping/refused");

    private String code;
    private String description;

    @JsonValue
    private String jsonValue;
}
