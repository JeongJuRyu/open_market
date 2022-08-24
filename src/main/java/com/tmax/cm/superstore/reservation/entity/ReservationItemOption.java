package com.tmax.cm.superstore.reservation.entity;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Getter
@Table(name = "reservationItemOption")
@Access(AccessType.FIELD)
public class ReservationItemOption {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "BINARY(16)")
	private UUID optionId;

	@Column
	private String optionName;

	@Column
	private String optionPrice;

	@Column
	private LocalTime startTime;

	@Column
	private LocalTime endTime;

	@Column
	private String optionDescription;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reservationItemId")
	private ReservationItem reservationItemId;
}
