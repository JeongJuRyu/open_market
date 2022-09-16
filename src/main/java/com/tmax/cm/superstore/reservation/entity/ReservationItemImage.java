package com.tmax.cm.superstore.reservation.entity;

import com.tmax.cm.superstore.reservation.dto.CreateReservationItemImageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Table(name = "reservationItemImage")
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "ReservationItemImageBuilder")
public class ReservationItemImage {

	@Id
	@Column(columnDefinition = "BINARY(16)")
	private UUID reservationItemImageId;

	@Column
	private String imageName;

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdDate;

	@LastModifiedDate
	@Column
	private LocalDateTime updatedDate;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reservationItemId")
	private ReservationItem reservationItemId;

	public static ReservationItemImageBuilder builder(
		CreateReservationItemImageDto.Request createReservationItemImageRequestDto, ReservationItem reservationItem) {
		return ReservationItemImageBuilder()
			.reservationItemImageId(createReservationItemImageRequestDto.getReservationItemImageId())
			.imageName(createReservationItemImageRequestDto.getImageName())
			.reservationItemId(reservationItem);
	}
}
