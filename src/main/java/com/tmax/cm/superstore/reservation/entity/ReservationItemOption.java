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
	@JoinColumn(name = "reservationItemId")
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
