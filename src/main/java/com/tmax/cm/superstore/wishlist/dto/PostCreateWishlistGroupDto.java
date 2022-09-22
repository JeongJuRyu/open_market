package com.tmax.cm.superstore.wishlist.dto;


import com.tmax.cm.superstore.wishlist.service.dto.CreateWishlistGroupDto;
import lombok.Getter;

import javax.validation.constraints.NotNull;

public class PostCreateWishlistGroupDto {

    @Getter
    @NotNull
    public static class Request implements CreateWishlistGroupDto {
        private String wishlistGroupName;
    }
}
