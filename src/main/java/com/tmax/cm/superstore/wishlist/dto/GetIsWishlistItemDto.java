package com.tmax.cm.superstore.wishlist.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetIsWishlistItemDto {

    @Builder
    @Getter
    public static class Response {

        @JsonProperty("result")
        Boolean result;
    }
}
