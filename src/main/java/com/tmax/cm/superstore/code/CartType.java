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

    public static CartType findBySendType(SendType sendType) {
        CartType cartType;

        switch (sendType) {
            case SHIPPING:
                cartType = CartType.SHIPPING_VISIT;
                break;
            case VISIT:
                cartType = CartType.SHIPPING_VISIT;
                break;
            case DELIVERY:
                cartType = CartType.DELIVERY_PICKUP;
                break;
            case PICKUP:
                cartType = CartType.DELIVERY_PICKUP;
                break;
            case RESERVATION:
                cartType = CartType.RESERVATION;
                break;
            default:
                cartType = null;
                break;
        }

        return cartType;
    }
}
