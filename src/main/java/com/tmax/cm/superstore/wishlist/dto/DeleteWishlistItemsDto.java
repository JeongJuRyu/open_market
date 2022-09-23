package com.tmax.cm.superstore.wishlist.dto;

import com.tmax.cm.superstore.wishlist.service.dto.DeleteWishlistItemDto;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;


public class DeleteWishlistItemsDto {

    @Getter
    @NotNull
    public static class Request implements DeleteWishlistItemDto {
        List<Long> wishlistItemIds;
    }

}
