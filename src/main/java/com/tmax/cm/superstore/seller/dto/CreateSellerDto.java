package com.tmax.cm.superstore.seller.dto;

import com.tmax.cm.superstore.seller.entity.Business;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.seller.entity.SellerDelivery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class CreateSellerDto {

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Request {
		private SellerInfo sellerInfo;
		private BizInfo bizInfo;
		private SellerDeliveryInfo sellerDeliveryInfo;
	}

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private UUID sellerId;
		private SellerInfo sellerInfo;
		private BizInfo bizinfo;
		private SellerDeliveryInfo sellerDeliveryInfo;
		private Boolean isDeleted;

		public static ResponseBuilder builder(Seller seller, Business business, SellerDelivery sellerDelivery) {
			return ResponseBuilder()
				.sellerId(seller.getSellerId())
				.bizinfo(BizInfo.builder(business).build())
				.sellerInfo(SellerInfo.builder(seller).build())
				.sellerDeliveryInfo(SellerDeliveryInfo.builder(sellerDelivery).build())
				.isDeleted(seller.isDeleted());
		}
	}

	@Builder(builderMethodName = "BizInfoBuilder")
	@Getter
	public static class BizInfo {
		private String bizNum;
		private String bizName;
		private String bizOwner;
		private String bizAddress;
		private String reportNumber;

		public static BizInfoBuilder builder(Business business) {
			return BizInfoBuilder()
				.bizNum(business.getBizNum())
				.bizName(business.getBizName())
				.bizOwner(business.getBizOwner())
				.bizAddress(business.getBizAddress())
				.reportNumber(business.getReportNumber());
		}
	}

	@Builder(builderMethodName = "SellerInfoBuilder")
	@Getter
	public static class SellerInfo {
		private String loginId;
		private String password;
		private String sellerName;
		private String sellerEmail;
		private String sellerPhoneNum;

		public static SellerInfoBuilder builder(Seller seller) {
			return SellerInfoBuilder()
				.loginId(seller.getLoginId())
				.password(seller.getPassword())
				.sellerName(seller.getSellerName())
				.sellerEmail(seller.getSellerEmail())
				.sellerPhoneNum(seller.getSellerPhoneNum());
		}
	}

	@Builder(builderMethodName = "SellerDeliveryInfoBuilder")
	@Getter
	public static class SellerDeliveryInfo {
		private String shipmentAddress;
		private String shipmentAddressDetail;
		private String returnAddress;
		private String returnAddressDetail;

		public static SellerDeliveryInfoBuilder builder(SellerDelivery sellerDelivery) {
			return SellerDeliveryInfoBuilder()
				.shipmentAddress(sellerDelivery.getShipmentAddress())
				.shipmentAddressDetail(sellerDelivery.getShipmentAddressDetail())
				.returnAddress(sellerDelivery.getReturnAddress())
				.returnAddressDetail(sellerDelivery.getReturnAddressDetail());
		}
	}
}
