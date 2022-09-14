package com.tmax.cm.superstore.cart.dto;

import java.util.List;
import java.util.UUID;

import com.tmax.cm.superstore.cart.service.dto.DeleteCartItemsDto;

import lombok.Getter;

public class DeleteCartItemsHttpDto {

    @Getter
    public static class Request implements DeleteCartItemsDto {

        private List<UUID> cartItemIds;
    }
}
