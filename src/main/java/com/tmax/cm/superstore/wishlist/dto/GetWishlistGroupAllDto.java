package com.tmax.cm.superstore.wishlist.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
public class GetWishlistGroupAllDto {

    @Builder
    @Getter
    public static class Response {

        @JsonProperty("wishlistGroupsList")
        private List<GetWishlistGroupsDto> wishlistGroupDtoList;

        @Builder
        @Getter
        public static class GetWishlistGroupsDto {

            @NotNull
            private Long wishlistGroupId;

            @NotNull
            private String wishlistGroupName;

            @NotNull
            @Builder.Default
            private String wishlistGroupThumbnailURL = "null";

            private Integer totalItemCount;
        }
    }
}
