package com.tmax.cm.superstore.cart.dto;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Getter;

public class PutCartReservationItemDto {

    @Getter
    public static class Request {

        private LocalDateTime reservationDate;

        private DayOfWeek dayOfWeek;

        private Integer reservationHeadcount;

        private String guestName;

        private String guestPhoneNumber;

        private String guestEmail;

        private String reservationRequirement;

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
