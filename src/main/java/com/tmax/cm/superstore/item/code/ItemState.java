package com.tmax.cm.superstore.item.code;

import com.fasterxml.jackson.annotation.JsonValue;
import com.tmax.cm.superstore.code.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ItemState implements Code {
    WAITING("WAITING", "판매대기", "waiting"),
    FOR_SALE("FOR_SALE", "판매중", "for_sale"),
    SOLD_OUT("SOLD_OUT", "품절", "sold_out"),
    APPROVING("APPROVING", "승인대기", "approving"),
    RESERVATION("RESERVATION", "예약", "reservation"),
    END_SALE("END_SALE", "판매종료", "end_sale"),
    SUSPENSION("SUSPENSION", "판매중지","suspension_sale"),
    PROHIBITING_SALE("PROHIBITING_SALE", "판매금지", "prohibition_sale");

    private String code;
    private String description;

    @JsonValue
    private String jsonValue;
}
