package com.tmax.cm.superstore.common.config.security.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private ResponseDto<String> responseDto = new ResponseDto<String>(ResponseCode.ERROR_UNAUTHENTICATED, null);
	private ObjectMapper mapper = new ObjectMapper();
	private static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8"; // should be deprecated

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {

		this.responseDto.setData(authException.getMessage());
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(APPLICATION_JSON_UTF8_VALUE);
		response.getWriter().write(this.mapper.writeValueAsString(this.responseDto));
	}
}
