package com.tmax.cm.superstore.item.dto;

import com.tmax.cm.superstore.code.SendType;
import com.tmax.cm.superstore.item.code.ItemState;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class GetItemSimpleDto {
    @Builder
    @Getter
    public static class Response {

        private UUID shopId;

        private String shopName;

        private Set<SendType> possibleSendType;

        private UUID itemId;

        private String itemName;

        private Integer itemPrice;

        private Long categoryId;

        private LocalDateTime createdAt;

        private LocalDateTime modifiedAt;

        private ItemState itemState;
    }
}
