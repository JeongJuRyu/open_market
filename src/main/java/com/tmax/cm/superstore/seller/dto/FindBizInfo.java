package com.tmax.cm.superstore.seller.dto;

import com.tmax.cm.superstore.seller.entity.Business;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

public class FindBizInfo {

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private UUID sellerId;
		private String bizNum;
		private String bizOwner;
		private String bizName;
		private String bizAddress;
		private String reportNumber;

		public static ResponseBuilder builder(Business business) {
			return ResponseBuilder()
				.sellerId(business.getSellerId().getSellerId())
				.bizNum(business.getBizNum())
				.bizOwner(business.getBizOwner())
				.bizName(business.getBizName())
				.bizAddress(business.getBizAddress())
				.reportNumber(business.getReportNumber());
		}
	}
}
