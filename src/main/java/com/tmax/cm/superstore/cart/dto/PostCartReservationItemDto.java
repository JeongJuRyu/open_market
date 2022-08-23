package com.tmax.cm.superstore.cart.dto;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Getter;

public class PostCartReservationItemDto {

    @Getter
    public static class Request {

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
        public static class PostSelectedOptionDto {

            private Integer selectedOptionCount;

            private List<PostCartOptionGroupDto> cartOptionGroups;

            @Getter
            public static class PostCartOptionGroupDto {

                private UUID optionGroupId;

                private List<PostCartOptionDto> cartOpions;

                @Getter
                public static class PostCartOptionDto {

                    private UUID optionId;

                    private Integer cartItemOptionCount;
                }
            }
        }
    }
}
