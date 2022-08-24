package com.tmax.cm.superstore.seller.dto;

import com.tmax.cm.superstore.seller.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.parameters.P;

import java.util.UUID;

public class CreateSellerDto {

	@Getter
	@AllArgsConstructor
	@Builder
	public static class Request{
		private String loginId;
		private String password;
		private String sellerName;
		private String sellerEmail;
		private String sellerPhoneNum;
	}

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response{
		private UUID sellerId;
		private String loginId;
		private String password;
		private String sellerName;
		private String sellerEmail;
		private String sellerPhoneNum;

		public static ResponseBuilder builder(Seller seller){
			return ResponseBuilder()
				.sellerId(seller.getSellerId())
				.loginId(seller.getLoginId())
				.password(seller.getPassword())
				.sellerName(seller.getSellerName())
				.sellerEmail(seller.getSellerEmail())
				.sellerPhoneNum(seller.getSellerPhoneNum());
		}
	}
}
