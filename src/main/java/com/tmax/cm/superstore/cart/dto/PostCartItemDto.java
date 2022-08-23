package com.tmax.cm.superstore.cart.dto;

import java.util.List;
import java.util.UUID;

import com.tmax.cm.superstore.code.SendType;

import lombok.Getter;

public class PostCartItemDto {

    @Getter
    public static class Request {

        private UUID itemId;

        private SendType sendType;

        private List<PostSelectedOptionDto> selectedOptions;

        @Getter
        public static class PostSelectedOptionDto {

            private Integer count;
            
            private List<PostCartOptionGroupDto> cartOptionGroups;

            @Getter
            public static class PostCartOptionGroupDto {

                private UUID optionGroupId;

                private List<PostCartOptionDto> cartOpions;

                @Getter
                public static class PostCartOptionDto {

                    private UUID optionId;

                    private Integer count;
                }
            }
        }
    }
}
