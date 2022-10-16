package com.tmax.cm.superstore.reservation.entity;

import com.tmax.cm.superstore.reservation.dto.CreateReservationItemOptionDto;
import com.tmax.cm.superstore.reservation.dto.ModifyReservationItemOptionDto;
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
@Table(name = "reservationItemOption")
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "ReservationItemOptionBuilder")
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
	private String optionDescription;

	@Column(nullable = false)
	private boolean isDeleted;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	// 자동 생성된 외래키명이 너무 길어서 오류나므로 명시적으로 지정
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_reservation_item_option_reservation_item_id"))
	private ReservationItem reservationItemId;

	public static ReservationItemOptionBuilder builder(
		CreateReservationItemOptionDto.Request createReservationItemOptionRequestDto, ReservationItem reservationItem) {
		return ReservationItemOptionBuilder()
			.optionName(createReservationItemOptionRequestDto.getOptionName())
			.optionPrice(createReservationItemOptionRequestDto.getOptionPrice())
			.optionDescription(createReservationItemOptionRequestDto.getOptionDescription())
			.reservationItemId(reservationItem);
	}

	public void modifyReservationItemOption(
		ModifyReservationItemOptionDto.Request modifyReservationItemOptionRequestDto) {
		this.optionName = modifyReservationItemOptionRequestDto.getOptionName();
		this.optionPrice = modifyReservationItemOptionRequestDto.getOptionPrice();
		this.optionDescription = modifyReservationItemOptionRequestDto.getOptionDescription();
	}

	public void deleteReservationItemOption() {
		this.isDeleted = true;
	}
}
