package com.tmax.cm.superstore.user.dto;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateDeliveryInfoRequestDto {
	@NotNull
	private UUID shippingAddressId;

	@NotNull
	private String email;

	@NotNull
	private String recipient;

	@NotNull
	private boolean isDefaultAddress;

	@NotNull
	private String mobile;

	@NotNull
	private String address;

	private String requests;
}
