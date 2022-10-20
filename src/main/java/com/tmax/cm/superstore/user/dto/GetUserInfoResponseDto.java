package com.tmax.cm.superstore.user.dto;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetUserInfoResponseDto {
	private String name;
	private String email;
	private String phoneNum;
	private List<GetUserDeliveryInfoResponseDto.DeliveryAddress> deliveryAddresses;
	// @Getter
	// @Builder
	// public static class DeliveryAddress{
	// 	private UUID id;
	// 	private String recipient;
	// 	private Boolean isDefaultAddress;
	// 	private String mobile;
	// 	private String address;
	// 	private String requests;
	// }
}
