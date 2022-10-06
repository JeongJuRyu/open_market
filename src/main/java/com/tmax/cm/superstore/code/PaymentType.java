package com.tmax.cm.superstore.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentType implements Code {
    DEPOSIT("DEPOSIT", "계좌이체"),
    CREDIT_CARD("CREDIT_CARD", "신용카드"),
    CORPORATION_CARD("CORPORATION_CARD", "법인카드"),
    MOBILE_CARRIER("MOBILE_CARRIER", "휴대폰 통신사 결제"),
    VIRTUAL_ACCOUNT_DEPOSIT("VIRTUAL_ACCOUNT_DEPOSIT", "무통장입금");

    private String code;
    private String description;
}
