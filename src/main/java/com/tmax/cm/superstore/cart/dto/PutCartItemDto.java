package com.tmax.cm.superstore.cart.dto;

import java.util.List;
import java.util.UUID;

import com.tmax.cm.superstore.code.SendType;

import lombok.Getter;

public class PutCartItemDto {

    @Getter
    public static class Request {

        private SendType sendType;

        private List<PutSelectedOptionDto> selectedOptions;

        @Getter
        public static class PutSelectedOptionDto {

            private Integer selectedOptionCount;
            
            private List<PutCartOptionGroupDto> cartOptionGroups;

            @Getter
            public static class PutCartOptionGroupDto {

                private UUID optionGroupId;

                private List<PutCartOptionDto> cartOpions;

                @Getter
                public static class PutCartOptionDto {

                    private UUID optionId;

                    private Integer cartItemOptionCount;
                }
            }
        }
    }
}
