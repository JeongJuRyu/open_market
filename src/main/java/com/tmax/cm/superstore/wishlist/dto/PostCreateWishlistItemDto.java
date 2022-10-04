package com.tmax.cm.superstore.wishlist.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.tmax.cm.superstore.wishlist.service.dto.CreateWishlistItemDto;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class PostCreateWishlistItemDto {

    @NotNull
    @Getter
    public static class Request implements CreateWishlistItemDto {

        @NotNull
        @JsonProperty("ItemId")
        private UUID itemId;
    }
}
