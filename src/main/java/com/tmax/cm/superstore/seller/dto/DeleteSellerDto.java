package com.tmax.cm.superstore.seller.dto;

import com.tmax.cm.superstore.seller.entity.Seller;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

public class DeleteSellerDto {

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private UUID sellerId;
		private String sellerName;
		private Boolean isDeleted;

		public static ResponseBuilder builder(Seller seller) {
			return ResponseBuilder()
				.sellerId(seller.getSellerId())
				.sellerName(seller.getSellerName())
				.isDeleted(seller.isDeleted());
		}
	}
}
