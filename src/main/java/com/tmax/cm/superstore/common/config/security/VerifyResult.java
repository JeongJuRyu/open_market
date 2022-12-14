package com.tmax.cm.superstore.common.config.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class VerifyResult {
	private boolean success;
	private String username;
}
