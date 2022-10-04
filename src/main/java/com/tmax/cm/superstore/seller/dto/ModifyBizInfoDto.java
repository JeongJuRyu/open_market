package com.tmax.cm.superstore.seller.dto;

import com.tmax.cm.superstore.seller.entity.Business;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class ModifyBizInfoDto {

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Request {
		private String bizNum;
		private String bizName;
		private String bizOwner;
		private String bizAddress;
		private String reportNumber;
		private String contactPerson;
	}

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private UUID sellerId;
		private String bizNum;
		private String bizOwner;
		private String bizName;
		private String bizAddress;
		private String reportNumber;
		private String contactPerson;

		public static ResponseBuilder builder(Business business) {
			return ResponseBuilder()
				.sellerId(business.getSellerId().getSellerId())
				.bizNum(business.getBizNum())
				.bizOwner(business.getBizOwner())
				.bizName(business.getBizName())
				.bizAddress(business.getBizAddress())
				.reportNumber(business.getReportNumber())
				.contactPerson(business.getContactPerson());
		}
	}
}
