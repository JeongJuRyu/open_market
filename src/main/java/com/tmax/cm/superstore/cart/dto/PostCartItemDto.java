package com.tmax.cm.superstore.cart.dto;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tmax.cm.superstore.cart.service.dto.CreateCartItemDto;
import com.tmax.cm.superstore.cart.service.dto.CreateCartOptionDto;
import com.tmax.cm.superstore.cart.service.dto.CreateCartOptionGroupDto;
import com.tmax.cm.superstore.cart.service.dto.CreateSelectedOptionDto;
import com.tmax.cm.superstore.code.SendType;

import lombok.Getter;

public class PostCartItemDto {

    @Getter
    @NotNull
    public static class Request implements CreateCartItemDto {

        @NotNull
        @JsonProperty("itemId")
        private UUID id;

        @NotNull
        private SendType sendType;

        @NotNull
        private List<PostSelectedOptionDto> selectedOptions;
        
        @Getter
        @NotNull
        public static class PostSelectedOptionDto implements CreateSelectedOptionDto {
            
            @NotNull
            private Integer count;
            
            private List<PostCartOptionGroupDto> cartOptionGroups;
            
            @Getter
            @NotNull
            public static class PostCartOptionGroupDto implements CreateCartOptionGroupDto {

                @JsonProperty("optionGroupId")
                private UUID id;

                private List<PostCartOptionDto> cartOptions;

                @Getter
                public static class PostCartOptionDto implements CreateCartOptionDto {

                    @JsonProperty("optionId")
                    private UUID id;

                    private Integer count;
                }
            }
        }
    }
}
