package com.tmax.cm.superstore.cart.entity.reservationCart;

import com.tmax.cm.superstore.code.SendType;
import com.tmax.cm.superstore.reservation.entity.ReservationItem;
import com.tmax.cm.superstore.reservation.entity.ReservationItemOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@Table(name = "reservationCartItem")
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "ReservationCartItemBuilder")
public class ReservationCartItem {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	private ReservationCart reservationCartId;

	@OneToOne
	@JoinColumn(nullable = false)
	private ReservationItem reservationItem;

	@OneToOne
	private CartReservationInfo cartReservationInfo;

	@OneToOne
	private ReservationItemOption reservationItemOption;

	@Enumerated(EnumType.STRING)
	private SendType sendType;
	// RESERVATION

	@Column(nullable = false)
	@Builder.Default
	private Boolean isDeleted = false;

	public static ReservationCartItemBuilder builder(ReservationCart reservationCart, ReservationItem reservationItem,
		CartReservationInfo cartReservationInfo, ReservationItemOption reservationItemOption, SendType sendType) {
		return ReservationCartItemBuilder()
			.reservationCartId(reservationCart)
			.reservationItem(reservationItem)
			.cartReservationInfo(cartReservationInfo)
			.reservationItemOption(reservationItemOption)
			.sendType(sendType);
	}

	public void update(ReservationItem reservationItem, ReservationItemOption reservationItemOption,
		CartReservationInfo cartReservationInfo) {
		this.reservationItem = reservationItem;
		this.cartReservationInfo = cartReservationInfo;
		this.reservationItemOption = reservationItemOption;
	}

	public void delete(){
		this.isDeleted = true;
	}
}
