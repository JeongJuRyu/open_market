package com.tmax.cm.superstore.mypage.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class PostItemInquiryRequestDto {
	private String title;

	private String content;

	private List<ItemInquiryImage> itemInquiryImages;

	@Getter
	public static class ItemInquiryImage {
		private String url;
	}
}
