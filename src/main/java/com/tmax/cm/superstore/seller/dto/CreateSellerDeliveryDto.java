package com.tmax.cm.superstore.seller.dto;

import com.tmax.cm.superstore.seller.entity.SellerDelivery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class CreateSellerDeliveryDto {

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Request {
		private String shipmentAddress;
		private String shipmentAddressDetail;
		private String returnAddress;
		private String returnAddressDetail;
	}

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private UUID sellerDeliveryId;
		private String shipmentAddress;
		private String shipmentAddressDetail;
		private String returnAddress;
		private String returnAddressDetail;
		private Boolean isRepresent;

		public static ResponseBuilder builder(SellerDelivery sellerDelivery) {
			return ResponseBuilder()
				.sellerDeliveryId(sellerDelivery.getSellerDeliveryId())
				.shipmentAddress(sellerDelivery.getShipmentAddress())
				.shipmentAddressDetail(sellerDelivery.getShipmentAddressDetail())
				.returnAddress(sellerDelivery.getReturnAddress())
				.returnAddressDetail(sellerDelivery.getReturnAddressDetail())
				.isRepresent(sellerDelivery.isRepresent());
		}
	}
}
