package com.tmax.cm.superstore.code;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReturnType implements Code {

    RETURN_WAITING("RETURN_WAITING", "반품 요청 대기", "return/waiting"),
    RETURN_ACCEPT("RETURN_ACCEPT", "반품 요청 수락", "return/accepted"),
    RETURN_REFUSED("RETURN_REFUSED", "반품 요청 거절", "return/refused"),
    RETURN_PROCEED("RETURN_PROCEED", "반품 진행 중", "return/proceed"),
    RETURN_DONE("RETURN_DONE", "반품 완료", "return/done");

    private String code;
    private String description;

    @JsonValue
    private String jsonValue;
}
