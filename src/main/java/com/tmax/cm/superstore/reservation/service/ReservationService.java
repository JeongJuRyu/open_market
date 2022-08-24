package com.tmax.cm.superstore.reservation.service;

import com.tmax.cm.superstore.reservation.repository.ReservationItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationItemRepository reservationItemRepository;

}
