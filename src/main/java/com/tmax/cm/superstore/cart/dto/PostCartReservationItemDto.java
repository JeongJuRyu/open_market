package com.tmax.cm.superstore.cart.dto;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tmax.cm.superstore.cart.service.dto.CreateCartOptionDto;
import com.tmax.cm.superstore.cart.service.dto.CreateCartOptionGroupDto;
import com.tmax.cm.superstore.cart.service.dto.CreateCartReservationItemDto;
import com.tmax.cm.superstore.cart.service.dto.CreateSelectedOptionDto;

import lombok.Getter;

public class PostCartReservationItemDto {

    @Getter
    public static class Request implements CreateCartReservationItemDto {

        @NotNull
        private UUID itemId;

        private LocalDateTime reservationDate;

        private DayOfWeek dayOfWeek;

        private Integer reservationHeadcount;

        private String guestName;

        private String guestPhoneNumber;

        private String guestEmail;

        private String reservationRequirement;

        private List<PostSelectedOptionDto> selectedOptions;

        @Getter
        public static class PostSelectedOptionDto implements CreateSelectedOptionDto {

            @JsonProperty("selectedOptionCount")
            private Integer count;

            private List<PostCartOptionGroupDto> cartOptionGroups;

            @Getter
            public static class PostCartOptionGroupDto implements CreateCartOptionGroupDto {

                @JsonProperty("optionGroupId")
                private UUID id;

                private List<PostCartOptionDto> cartOptions;

                @Getter
                public static class PostCartOptionDto implements CreateCartOptionDto {

                    @JsonProperty("optionId")
                    private UUID id;

                    @JsonProperty("cartItemOptionCount")
                    private Integer count;
                }
            }
        }
    }
}
