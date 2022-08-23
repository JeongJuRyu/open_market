package com.tmax.cm.superstore.item.dto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tmax.cm.superstore.code.SendType;

import lombok.Builder;
import lombok.Getter;

public class GetItemAllDto {
    @Builder
    @Getter
    public static class Response {

        private List<GetItemDto> items;

        @Builder
        @Getter
        public static class GetItemDto {

            private UUID shopId;

            private String shopName;

            private Set<SendType> possibleSendType;

            private UUID itemId;

            private String itemName;

            private Integer itemPrice;

            @JsonProperty("necessaryOptionGroups")
            private List<GetOptionGroupDto> necessaryOptionGroups;

            @JsonProperty("optionalOptionGroups")
            private List<GetOptionGroupDto> optionalOptionGroups;

            @Builder
            @Getter
            public static class GetOptionGroupDto {

                private UUID optionGroupId;

                private String optionGroupName;

                private List<GetOptionDto> options;

                @Builder
                @Getter
                public static class GetOptionDto {

                    private UUID optionId;

                    private String optionName;

                    private Integer optionPrice;

                    private String optionDescription;
                }
            }
        }
    }
}
