package com.tmax.cm.superstore.seller.dto;

import com.tmax.cm.superstore.seller.entity.Seller;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

public class LoginSellerDto {

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private String loginId;
		private UUID sellerId;

		public static ResponseBuilder builder(Seller seller) {
			return ResponseBuilder()
				.loginId(seller.getLoginId())
				.sellerId(seller.getSellerId());
		}
	}
}
