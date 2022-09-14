package com.tmax.cm.superstore.cart.service.dto;

import java.util.List;
import java.util.UUID;

public interface DeleteCartItemsDto {
    
    List<UUID> getCartItemIds();
}
