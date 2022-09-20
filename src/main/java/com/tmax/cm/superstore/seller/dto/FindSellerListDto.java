package com.tmax.cm.superstore.seller.dto;

import com.tmax.cm.superstore.seller.entity.Seller;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

public class FindSellerListDto {

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private List<SellerList> sellerList;

		public static ResponseBuilder builder(List<SellerList> sellerList) {
			return ResponseBuilder()
				.sellerList(sellerList);
		}

		@Getter
		@Builder(builderMethodName = "SellerListBuilder")
		public static class SellerList {
			private UUID sellerId;
			private String sellerName;
			private String sellerEmail;
			private String sellerPhoneNum;

			public static SellerListBuilder builder(Seller seller) {
				return SellerListBuilder()
					.sellerId(seller.getSellerId())
					.sellerName(seller.getSellerName())
					.sellerEmail(seller.getSellerEmail())
					.sellerPhoneNum(seller.getSellerPhoneNum());
			}
		}
	}
}
