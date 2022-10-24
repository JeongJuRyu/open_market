package com.tmax.cm.superstore.user.dto;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateDeliveryInfoRequestDto {
	@NotNull
	private UUID deliveryAddressId;

	@NotNull
	private String recipient;

	@NotNull
	private String mobile;

	@NotNull
	private Boolean isDefaultAddress;

	@NotNull
	private String address;

	private String requests;
}
