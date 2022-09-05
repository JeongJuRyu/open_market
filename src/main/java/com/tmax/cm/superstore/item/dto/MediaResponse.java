package com.tmax.cm.superstore.item.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
public class MediaResponse {

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Single {
		private int code;
		private String message;
		private FileInfo data;
	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Multi {
		private int code;
		private String message;
		private List<FileInfo> data;
	}
}
