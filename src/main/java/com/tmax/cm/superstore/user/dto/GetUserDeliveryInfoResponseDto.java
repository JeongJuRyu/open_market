package com.tmax.cm.superstore.user.dto;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;

import org.springframework.web.bind.annotation.GetMapping;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetUserDeliveryInfoResponseDto {
	private List<DeliveryAddress> deliveryAddresses;
	@Getter
	@Builder
	public static class DeliveryAddress {
		private UUID id;

		private String recipient;

		private Boolean isDefaultAddress;

		private String mobile;

		private String address;

		private String requests;
	}
}
