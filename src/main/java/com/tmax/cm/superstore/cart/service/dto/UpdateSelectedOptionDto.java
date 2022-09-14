package com.tmax.cm.superstore.cart.service.dto;

import java.util.List;

public interface UpdateSelectedOptionDto {

    Integer getSelectedOptionCount();

    <T extends UpdateCartOptionGroupDto> List<T> getCartOptionGroups();
}
