package com.tmax.cm.superstore.cart.entity.reservationCart;

import com.tmax.cm.superstore.cart.dto.reservationCart.PatchReservationCartItem;
import com.tmax.cm.superstore.cart.dto.reservationCart.PostReservationCartItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Table(name = "CartReservationInfo")
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "CartReservationInfoBuilder")
public class CartReservationInfo {

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

	public static CartReservationInfoBuilder builder(
		PostReservationCartItemDto.Request postReservationCartItemRequestDto) {
		return CartReservationInfoBuilder()
			.reservationDay(postReservationCartItemRequestDto.getCartReservationInfoRequest().getReservationDay())
			.reservationTime(postReservationCartItemRequestDto.getCartReservationInfoRequest().getReservationTime())
			.numberOfPeople(postReservationCartItemRequestDto.getCartReservationInfoRequest().getNumberOfPeople())
			.customerRequest(postReservationCartItemRequestDto.getCartReservationInfoRequest().getCustomerRequest());
	}

	public void update(PatchReservationCartItem.Request patchReservationCartItemRequestDto) {
		this.reservationDay = patchReservationCartItemRequestDto.getCartReservationInfoRequest().getReservationDay();
		this.reservationTime = patchReservationCartItemRequestDto.getCartReservationInfoRequest().getReservationTime();
		this.numberOfPeople = patchReservationCartItemRequestDto.getCartReservationInfoRequest().getNumberOfPeople();
		this.customerRequest = patchReservationCartItemRequestDto.getCartReservationInfoRequest().getCustomerRequest();
	}
}
