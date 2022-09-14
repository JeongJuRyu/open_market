package com.tmax.cm.superstore.cart.service.dto;

import java.util.List;
import java.util.UUID;

public interface CreateCartOptionGroupDto {

    UUID getId();

    <T extends CreateCartOptionDto> List<T> getCartOptions();
}
