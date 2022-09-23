package com.tmax.cm.superstore.wishlist.dto;


import com.tmax.cm.superstore.wishlist.service.dto.CreateWishlistGroupDto;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

public class PostCreateWishlistGroupDto {

    @Getter
    @NotNull
    public static class Request implements CreateWishlistGroupDto {
        private String wishlistGroupName;
    }


    @Builder
    @Getter
    public static class Response {
        private Long wishlistGroupId;
    }

}
