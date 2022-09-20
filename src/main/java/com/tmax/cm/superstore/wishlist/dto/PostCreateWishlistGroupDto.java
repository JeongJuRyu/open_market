package com.tmax.cm.superstore.wishlist.dto;


import com.tmax.cm.superstore.wishlist.service.dto.CreateWishlistGroupDto;
import lombok.Getter;

public class PostCreateWishlistGroupDto {

    @Getter
    public static class Request implements CreateWishlistGroupDto {
        private String WishListGroupName;
    }
}
