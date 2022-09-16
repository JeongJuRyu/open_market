package com.tmax.cm.superstore.reservation.entity;

import com.tmax.cm.superstore.reservation.dto.CreateReservationItemDto;
import com.tmax.cm.superstore.reservation.dto.MakeReservationDto;
import com.tmax.cm.superstore.seller.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Table(name = "reservation")
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "ReservationBuilder")
public class Reservation {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "BINARY(16)")
	private UUID reservationId;

	@Column
	private LocalDateTime reservationTime;

	@Column
	private Integer numberOfPeople;

	@Column
	private String customerRequest;

//	@Column
//	private String managerMemo;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sellerId")
	private Seller sellerId;

	@NotNull
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "reservationItemId")
	private ReservationItem reservationItemId;

	@NotNull
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "reservationItemOptionId")
	private ReservationItemOption reservationItemOptionId;

	public static ReservationBuilder builder(MakeReservationDto.Request makeReservationRequestDto, ReservationItem reservationItem, ReservationItemOption reservationItemOption, Seller seller) {
		return ReservationBuilder()
			.reservationTime(makeReservationRequestDto.getReservationTime())
			.numberOfPeople(makeReservationRequestDto.getNumberOfPeople())
			.customerRequest(makeReservationRequestDto.getCustomerRequest())
			.sellerId(seller)
			.reservationItemId(reservationItem)
			.reservationItemOptionId(reservationItemOption);
	}
}
