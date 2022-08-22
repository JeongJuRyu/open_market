package com.tmax.cm.superstore.cart.dto;

import java.util.List;
import java.util.UUID;

import lombok.Getter;

public class DeleteCartItemsDto {

    @Getter
    public static class Request {

        private List<UUID> cartItemIds;
    }
}
