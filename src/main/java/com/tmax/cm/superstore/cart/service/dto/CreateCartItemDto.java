package com.tmax.cm.superstore.cart.service.dto;

import java.util.List;
import java.util.UUID;

import com.tmax.cm.superstore.code.SendType;

public interface CreateCartItemDto {

    UUID getId();

    SendType getSendType();

    <T extends CreateSelectedOptionDto> List<T> getSelectedOptions();
}
