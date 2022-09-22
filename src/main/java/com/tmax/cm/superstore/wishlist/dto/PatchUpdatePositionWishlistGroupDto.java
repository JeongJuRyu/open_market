package com.tmax.cm.superstore.wishlist.dto;

import com.tmax.cm.superstore.wishlist.service.dto.UpdateWishlistGroupOrderDto;
import lombok.Getter;

import java.util.List;

public class PatchUpdatePositionWishlistGroupDto {

    @Getter
    public static class Request implements UpdateWishlistGroupOrderDto {
        private List<Long> wishlistGroupIds;
    }
}
