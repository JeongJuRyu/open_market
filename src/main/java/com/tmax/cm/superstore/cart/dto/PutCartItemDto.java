package com.tmax.cm.superstore.cart.dto;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tmax.cm.superstore.cart.service.dto.CreateCartOptionDto;
import com.tmax.cm.superstore.cart.service.dto.CreateCartOptionGroupDto;
import com.tmax.cm.superstore.cart.service.dto.CreateSelectedOptionDto;
import com.tmax.cm.superstore.cart.service.dto.UpdateCartItemDto;
import com.tmax.cm.superstore.code.SendType;

import lombok.Getter;

public class PutCartItemDto {

    @Getter
    public static class Request implements UpdateCartItemDto {

        private SendType sendType;

        private List<PutSelectedOptionDto> selectedOptions;

        @Getter
        public static class PutSelectedOptionDto implements CreateSelectedOptionDto {

            @JsonProperty("selectedOptionCount")
            private Integer count;
            
            private List<PutCartOptionGroupDto> cartOptionGroups;

            @Getter
            public static class PutCartOptionGroupDto implements CreateCartOptionGroupDto {

                @JsonProperty("optionGroupId")
                private UUID id;

                private List<PutCartOptionDto> cartOptions;

                @Getter
                public static class PutCartOptionDto implements CreateCartOptionDto {

                    @JsonProperty("optionId")
                    private UUID id;

                    @JsonProperty("cartItemOptionCount")
                    private Integer count;
                }
            }
        }
    }
}
