package com.tmax.cm.superstore.seller.dto;

import com.tmax.cm.superstore.seller.entity.SellerDelivery;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

public class FindSellerDeliveryListDto {

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private List<SellerDeliveryList> sellerDeliveryList;

		public static ResponseBuilder builder(List<SellerDeliveryList> sellerDeliveryList) {
			return ResponseBuilder()
				.sellerDeliveryList(sellerDeliveryList);
		}

		@Getter
		@Builder(builderMethodName = "SellerDeliveryListBuilder")
		public static class SellerDeliveryList {
			private UUID sellerDeliveryId;
			private String shipmentAddress;
			private String shipmentAddressDetail;
			private String returnAddress;
			private String returnAddressDetail;

			public static SellerDeliveryListBuilder builder(SellerDelivery sellerDelivery) {
				return SellerDeliveryListBuilder()
					.sellerDeliveryId(sellerDelivery.getSellerDeliveryId())
					.shipmentAddress(sellerDelivery.getShipmentAddress())
					.shipmentAddressDetail(sellerDelivery.getShipmentAddressDetail())
					.returnAddress(sellerDelivery.getReturnAddress())
					.returnAddressDetail(sellerDelivery.getReturnAddressDetail());
			}
		}
	}
}
