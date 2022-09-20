package com.tmax.cm.superstore.wishlist.service.dto;

import java.util.List;

public interface UpdateWishlistItemMoveDto {
    Long getTargetWishlistGroupId();
    List<Long> getWishlistItemIds();
}
