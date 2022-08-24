package com.tmax.cm.superstore.reservation.entity;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Getter
@Table(name = "reservationItem")
@Access(AccessType.FIELD)
public class ReservationItem {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "BINARY(16)")
	private UUID reservationItemId;

	@Column
	private String reservationItemName;

	@Column
	private String reservationItemDescription;

	@Column
	private String reservationItemNotice;

	@Column
	private Integer allowReservationNumberPer30;

	@Column
	private String reservationInterval;

	@Column
	private LocalTime startTime;

	@Column
	private LocalTime endTime;
}
