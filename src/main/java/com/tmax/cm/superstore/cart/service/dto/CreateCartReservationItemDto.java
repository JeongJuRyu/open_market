package com.tmax.cm.superstore.cart.service.dto;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface CreateCartReservationItemDto {

    UUID getItemId();

    LocalDateTime getReservationDate();

    DayOfWeek getDayOfWeek();

    Integer getReservationHeadcount();

    String getGuestName();

    String getGuestPhoneNumber();

    String getGuestEmail();

    String getReservationRequirement();

    <T extends CreateSelectedOptionDto> List<T> getSelectedOptions();
}
