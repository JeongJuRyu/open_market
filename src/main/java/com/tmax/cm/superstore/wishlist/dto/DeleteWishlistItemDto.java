package com.tmax.cm.superstore.wishlist.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tmax.cm.superstore.wishlist.service.dto.DeleteWishlistItemByItemIdDto;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@NotNull
public class DeleteWishlistItemDto {

    @Getter
    @NotNull
    public static class Request implements DeleteWishlistItemByItemIdDto {

        @NotNull
        @JsonProperty("ItemId")
        private UUID itemId;
    }
}
