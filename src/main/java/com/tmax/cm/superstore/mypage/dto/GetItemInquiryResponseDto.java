package com.tmax.cm.superstore.mypage.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class GetItemInquiryResponseDto {
	private ItemInquiry itemInquiry;

	@Getter
	@Builder
	public static class ItemInquiry {
		private UUID id;

	}
}
