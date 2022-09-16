package com.tmax.cm.superstore.cart.service.dto;

import java.util.List;

import com.tmax.cm.superstore.code.SendType;

public interface UpdateCartItemDto {

    SendType getSendType();

    <T extends CreateSelectedOptionDto> List<T> getSelectedOptions();
}
