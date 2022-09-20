package com.tmax.cm.superstore.wishlist.dto;

import com.tmax.cm.superstore.wishlist.service.dto.UpdateWishlistItemMoveDto;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

public class PatchUpdateMoveItemDto {

    @Getter
    public static class Request implements UpdateWishlistItemMoveDto {
        @NotNull
        private Long targetWishlistGroupId;

        private List<Long> wishlistItemIds;
    }
}
