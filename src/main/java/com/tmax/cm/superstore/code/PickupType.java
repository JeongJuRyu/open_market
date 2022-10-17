package com.tmax.cm.superstore.code;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PickupType {
    PICKUP_WAITING("PICKUP_WAITING", "주문 접수", "pickup/waiting"),
    PICKUP_ACCEPT("PICKUP_ACCEPT", "접수 완료 및 준비 중", "pickup/accepted"),
    PICKUP_READY("PICKUP_READY", "준비 완료", "pickup/done"),
    PICKUP_REFUSE("PICKUP_REFUSE", "접수 취소", "pickup/refused"),
    PICKUP_DONE("PICKUP_DONE", "수령 완료", "pickup/done");

    private String code;
    private String description;

    @JsonValue
    private String jsonValue;

}
