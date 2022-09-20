package com.tmax.cm.superstore.reservation.entity;

import com.tmax.cm.superstore.reservation.dto.CreateReservationItemDto;
import com.tmax.cm.superstore.seller.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Getter
@Table(name = "reservationItem")
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "ReservationItemBuilder")
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
	private Integer allowReservationNumberPerInterval;

	@Column
	private String reservationInterval;

	@Column(nullable = false)
	private boolean isDeleted;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sellerId")
	private Seller sellerId;

	@Column
	private LocalTime startTime;
	@Column
	private LocalTime endTime;

	public static ReservationItemBuilder builder(CreateReservationItemDto.Request createReservationItemRequestDto,
		Seller seller) {
		return ReservationItemBuilder()
			.reservationItemName(createReservationItemRequestDto.getReservationItemName())
			.reservationItemDescription(createReservationItemRequestDto.getReservationItemDescription())
			.reservationItemNotice(createReservationItemRequestDto.getReservationItemNotice())
			.allowReservationNumberPerInterval(createReservationItemRequestDto.getAllowReservationNumberPerInterval())
			.reservationInterval(createReservationItemRequestDto.getReservationInterval())
			.startTime(createReservationItemRequestDto.getStartTime())
			.endTime(createReservationItemRequestDto.getEndTime())
			.sellerId(seller);
	}
}