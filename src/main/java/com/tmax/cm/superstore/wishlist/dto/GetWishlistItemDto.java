package com.tmax.cm.superstore.wishlist.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tmax.cm.superstore.wishlist.entity.WishlistGroup;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class GetWishlistItemDto {

    @Builder
    @Getter
    public static class Response {

        @JsonProperty("wishlistItems")
        private List<WishlistItemDto> wishlistItemDtos;

        @JsonProperty("wishlistGroupList")
        private List<WishlistGroupDto> wishlistGroupDtos;

        @Builder
        @Getter
        public static class WishlistItemDto {
            @NotNull
            private Long wishlistItemId;

            @NotNull
            private Long wishlistGroupId;

            @NotNull
            private String wishlistGroupName;

            @NotNull
            private UUID shopId;

            @NotNull
            private String shopName;

            @NotNull
            private UUID itemId;

            @NotNull
            private String itemThumbnailURL;

            @NotNull
            private String itemName;

            @NotNull
            private Integer itemPrice;

            @NotNull
            private Integer categoryId;

            @NotNull
            private String categoryName;
        }

        @Getter
        @Builder
        public static class WishlistGroupDto {

            @NotNull
            private Long wishlistGroupId;

            @NotNull
            private String wishlistGroupName;

            @NotNull
            private String wishlistGroupThumbnailURL;

            private Integer totalItemCount;
        }
    }
}
