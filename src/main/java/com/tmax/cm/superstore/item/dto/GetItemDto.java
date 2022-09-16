package com.tmax.cm.superstore.item.dto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tmax.cm.superstore.code.SendType;

import com.tmax.cm.superstore.mypage.entity.Review;
import lombok.Builder;
import lombok.Getter;

public class GetItemDto {
    
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

        @JsonProperty("necessaryOptionGroups")
        private List<GetOptionGroupDto> necessaryOptionGroups;

        @JsonProperty("optionalOptionGroups")
        private List<GetOptionGroupDto> optionalOptionGroups;

        private List<ItemImageInfo> itemImages;

        private List<GetOptionGroupDto> optionGroups;

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
