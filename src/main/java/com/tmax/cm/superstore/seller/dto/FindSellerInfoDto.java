package com.tmax.cm.superstore.seller.dto;

import com.tmax.cm.superstore.seller.entity.Seller;
import lombok.Builder;
import lombok.Getter;

public class FindSellerInfoDto {

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private String sellerName;
		private String sellerEmail;
		private String sellerPhoneNum;
		private String address;

		public static ResponseBuilder builder(Seller seller) {
			return ResponseBuilder()
				.sellerName(seller.getSellerName())
				.sellerEmail(seller.getSellerEmail())
				.sellerPhoneNum(seller.getSellerPhoneNum())
				.address(seller.getAddress());
		}
	}
}
