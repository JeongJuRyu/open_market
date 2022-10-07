package com.tmax.cm.superstore.order.service.dto;

import java.util.List;
import java.util.UUID;

public interface CreatePurchaseOrderFromCartDto {
    
    List<UUID> getCartItemIds();
}
