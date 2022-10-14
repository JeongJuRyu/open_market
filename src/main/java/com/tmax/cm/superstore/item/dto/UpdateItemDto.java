package com.tmax.cm.superstore.item.dto;

import com.tmax.cm.superstore.category.entity.Category;
import com.tmax.cm.superstore.item.code.ItemState;
import com.tmax.cm.superstore.code.SendType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class UpdateItemDto {

    @Getter
    @Builder
    public static class Request {
        private UUID shopId;

        private String name;

        private Integer price;

        private Set<SendType> possibleSendType;

        private ItemState itemState;

        private Long categoryId;

        private List<PostOptionGroupDto> optionGroups;

        @Getter
        public static class PostOptionGroupDto {

            private String name;

            private Boolean isNecessary;

            private List<PostOptionDto> options;

            @Getter
            public static class PostOptionDto {

                private String name;

                private Integer price;

                private String description;
            }
        }
    }

    @Getter
    @Builder
    public static class Response {
        UUID itemId;
    }

}
