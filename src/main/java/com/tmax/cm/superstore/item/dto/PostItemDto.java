package com.tmax.cm.superstore.item.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;

public class PostItemDto {
    
	@Getter
    public static class Request {

        @NotNull
        private String name;

        @NotNull
        @PositiveOrZero
        private Integer price;

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
            }
        }
    }
}
