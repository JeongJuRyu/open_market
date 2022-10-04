package com.tmax.cm.superstore.user.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteDeliveryInfoRequestDto {
	private String email;
	private UUID shippingAddressId;
}
