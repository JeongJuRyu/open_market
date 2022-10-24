package com.tmax.cm.superstore.code;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CancelType implements Code {

    CANCEL_WAITING("CANCEL_WAITING", "취소 신청", "cancel/waiting"),
    CANCEL_DONE("CANCEL_DONE", "취소 완료", "cancel/done");

    private String code;
    private String description;

    @JsonValue
    private String jsonValue;
}
