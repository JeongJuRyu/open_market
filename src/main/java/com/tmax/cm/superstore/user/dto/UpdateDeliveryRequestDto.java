package com.tmax.cm.superstore.user.dto;

import java.util.List;

import javax.persistence.Column;

import lombok.Getter;

@Getter
public class UpdateDeliveryRequestDto {
	private String email;

	private String name;

	private boolean isBasicAddress;

	private String phoneNum;

	private String address;

	private List<DeliveryAddress> deliveryAddresses;

	@Getter
	public static class DeliveryAddress {
		private String name;

		private boolean isBasicAddress;

		private String phoneNum;

		private String address;
	}
}
