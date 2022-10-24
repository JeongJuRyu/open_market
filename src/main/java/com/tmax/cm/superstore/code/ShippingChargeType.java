package com.tmax.cm.superstore.code;


import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ShippingChargeType {
    FREE_SHIPPING("FREE_SHIPPING", "무료 배송", "free_shipping"),
    NOT_FREE_SHIPPING("NOT_FREE_SHIPPING", "유료 배송", "not_free_shipping");

    private String code;
    private String description;

    @JsonValue
    private String jsonValue;
}
