package com.tmax.cm.superstore.reservation.repository;

import com.tmax.cm.superstore.reservation.entity.Reservation;
import com.tmax.cm.superstore.reservation.entity.ReservationItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

	Optional<List<Reservation>> findAllByReservationItemIdAndReservationTime(ReservationItem reservationItemId,
		LocalDateTime reservationTime);

}