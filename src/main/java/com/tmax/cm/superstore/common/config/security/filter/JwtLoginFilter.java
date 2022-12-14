package com.tmax.cm.superstore.common.config.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.config.security.JwtUtil;
import com.tmax.cm.superstore.error.lib.AuthErrorCode;
import com.tmax.cm.superstore.user.dto.UserLoginRequestDto;
import com.tmax.cm.superstore.user.entities.User;
import com.tmax.cm.superstore.user.entities.UserLoginInfo;
import com.tmax.cm.superstore.user.repository.UserLoginInfoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

	private final ObjectMapper objectMapper = new ObjectMapper();
	private final UserLoginInfoRepository userLoginInfoRepository;

	public JwtLoginFilter(AuthenticationManager authenticationManager,
			UserLoginInfoRepository userLoginInfoRepository) {

		// 해당 경로에만 JwtLoginFilter 동작
		super(new AntPathRequestMatcher("/v1/auth/login", "POST"), authenticationManager);

		this.userLoginInfoRepository = userLoginInfoRepository;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		UserLoginRequestDto userLoginRequestDto;
		try {
			userLoginRequestDto = objectMapper.readValue(request.getInputStream(), UserLoginRequestDto.class);
		} catch (IOException e) {
			log.error(AuthErrorCode.NEED_CREDENTIAL.getMessage(), e);
			request.setAttribute("exception", AuthErrorCode.NEED_CREDENTIAL);
			throw new AuthenticationException(AuthErrorCode.NEED_CREDENTIAL.getMessage()) {
			};
		}

		// 3개의 파라미터를 모두 넘겨야 isAuthenticated가 true가 된다.
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				userLoginRequestDto.getEmail(),
				userLoginRequestDto.getPassword());
		return getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User user = (User) authResult.getPrincipal();
		UserLoginInfo userLoginInfo = userLoginInfoRepository.findById(user.getEmail()).orElse(null);
		if (userLoginInfo == null) {
			userLoginInfo = UserLoginInfo.builder().email(user.getEmail())
					.accessToken(JwtUtil.createAccessToken(user))
					.build();
		}
		userLoginInfoRepository.save(userLoginInfo);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.getOutputStream().write(objectMapper.writeValueAsBytes(userLoginInfo));
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		response.setStatus(HttpStatus.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		if (failed.getMessage().equals("wrong password")) {
			response.getOutputStream().write(objectMapper.writeValueAsBytes(ResponseCode.ERROR_PASSWORD_NOT_MATCHED));
		} else if (failed.getMessage().equals("email not found")) {
			response.getOutputStream().write(objectMapper.writeValueAsBytes(ResponseCode.ERROR_EMAIL_NOT_FOUND));
		}
	}
}
