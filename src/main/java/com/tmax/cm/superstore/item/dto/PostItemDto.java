package com.tmax.cm.superstore.item.dto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.tmax.cm.superstore.item.code.ItemState;
import com.tmax.cm.superstore.code.SendType;

import lombok.*;

public class PostItemDto {

    @Getter
    public static class Request {
//
//        @NotNull
//        private String shopName; // TODO 가게 생성 타 API로 분리

        @NotNull
        private String name;

        @NotNull
        @PositiveOrZero
        private Integer price;

        private Set<SendType> possibleSendType;

        private List<PostOptionGroupDto> optionGroups;

        @NotNull
        private Long categoryId;

        @NotNull
        private ItemState itemState;

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

    @Builder
    @Getter
    public static class Response {

        private UUID itemId;
    }
}
