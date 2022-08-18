package com.tmax.cm.superstore.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SendType implements Code {
    SHIPPING("SHIPPING", "배송"),
    VISIT("VISIT", "방문수령"),
    DELIVERY("DELIVERY", "배달"),
    PICKUP("PICKUP", "픽업"),
    RESERVATION("RESERVATION", "예약");

    private String code;
    private String description;
}
