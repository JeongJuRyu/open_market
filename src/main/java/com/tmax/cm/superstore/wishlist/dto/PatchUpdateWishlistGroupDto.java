package com.tmax.cm.superstore.wishlist.dto;

import com.tmax.cm.superstore.wishlist.service.dto.UpdateWishlistGroupDto;
import lombok.Getter;

import javax.validation.constraints.NotNull;

public class PatchUpdateWishlistGroupDto {

    @Getter
    @NotNull
    public static class Request implements UpdateWishlistGroupDto {
        private String wishlistGroupName;
    }
}
