package com.tmax.cm.superstore.wishlist.dto;

import com.tmax.cm.superstore.wishlist.service.dto.UpdateWishlistGroupDto;
import lombok.Getter;

public class PatchUpdateWishlistGroupDto {

    @Getter
    public static class Request implements UpdateWishlistGroupDto {
        private String WishListGroupName;
    }
}
