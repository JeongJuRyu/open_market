package com.tmax.cm.superstore.reservation.entity;

import com.tmax.cm.superstore.cart.entity.reservationCart.ReservationCartItem;
import com.tmax.cm.superstore.reservation.dto.MakeReservationDto;
import com.tmax.cm.superstore.reservation.dto.ModifyReservationDto;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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
	private LocalDate reservationDay;

	@Column
	private LocalDateTime reservationTime;

	@Column
	private Integer numberOfPeople;

	@Column
	private String customerRequest;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	private Seller sellerId;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User userId;

	@NotNull
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn()
	private ReservationItem reservationItemId;

	@NotNull
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn()
	private ReservationItemOption reservationItemOptionId;

	public static ReservationBuilder builder(MakeReservationDto.Request makeReservationRequestDto,
		ReservationItem reservationItem, ReservationItemOption reservationItemOption, Seller seller, User user) {
		return ReservationBuilder()
			.reservationDay(LocalDate.now())
			.reservationTime(makeReservationRequestDto.getReservationTime())
			.numberOfPeople(makeReservationRequestDto.getNumberOfPeople())
			.customerRequest(makeReservationRequestDto.getCustomerRequest())
			.sellerId(seller)
			.userId(user)
			.reservationItemId(reservationItem)
			.reservationItemOptionId(reservationItemOption);
	}

	public void modifyReservation(ModifyReservationDto.Request modifyReservationRequestDto,
		ReservationItem reservationItem, ReservationItemOption reservationItemOption) {
		this.reservationDay = LocalDate.now();
		this.reservationTime = modifyReservationRequestDto.getReservationTime();
		this.numberOfPeople = modifyReservationRequestDto.getNumberOfPeople();
		this.customerRequest = modifyReservationRequestDto.getCustomerRequest();
		this.reservationItemId = reservationItem;
		this.reservationItemOptionId = reservationItemOption;
	}
}
