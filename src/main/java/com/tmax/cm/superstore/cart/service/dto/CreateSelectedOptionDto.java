package com.tmax.cm.superstore.cart.service.dto;

import java.util.List;

public interface CreateSelectedOptionDto {

    Integer getCount();

    <T extends CreateCartOptionGroupDto> List<T> getCartOptionGroups();
}
