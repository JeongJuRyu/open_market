package com.tmax.cm.superstore.common.config.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmax.cm.superstore.common.config.security.JwtUtil;
import com.tmax.cm.superstore.common.config.security.VerifyResult;
import com.tmax.cm.superstore.error.lib.AuthErrorCode;
import com.tmax.cm.superstore.user.dto.TokenRequestDto;
import com.tmax.cm.superstore.user.entities.User;
import com.tmax.cm.superstore.user.entities.UserLoginInfo;
import com.tmax.cm.superstore.user.repository.UserLoginInfoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtRefreshTokenFilter extends AbstractAuthenticationProcessingFilter {

	private final ObjectMapper objectMapper = new ObjectMapper();
	private final UserDetailsService userDetailsService;
	private final UserLoginInfoRepository userLoginInfoRepository;

	public JwtRefreshTokenFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
			UserLoginInfoRepository userLoginInfoRepository) {
		// 해당 경로에만 JwtLoginFilter 동작
		super(new AntPathRequestMatcher("/v1/auth/reissue", "POST"), authenticationManager);

		this.userDetailsService = userDetailsService;
		this.userLoginInfoRepository = userLoginInfoRepository;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		TokenRequestDto tokenRequestDto;
		try {
			tokenRequestDto = objectMapper.readValue(request.getInputStream(), TokenRequestDto.class);
		} catch (IOException e) {
			log.error(AuthErrorCode.NEED_CREDENTIAL.getMessage(), e);
			request.setAttribute("exception", AuthErrorCode.NEED_CREDENTIAL);
			throw new AuthenticationException(AuthErrorCode.NEED_CREDENTIAL.getMessage()) {
			};
		}
		String convertedToken = JwtUtil.resolveToken(request);

		if (tokenRequestDto.getAccessToken() == null && convertedToken == null) {
			log.error(AuthErrorCode.NEED_ACCESS_TOKEN.getMessage());
			throw new AuthenticationException(AuthErrorCode.NEED_ACCESS_TOKEN.getMessage()) {
			};
		}
		if (tokenRequestDto.getRefreshToken() == null)

		{
			log.error(AuthErrorCode.NEED_REFRESH_TOKEN.getMessage());
			throw new AuthenticationException(AuthErrorCode.NEED_REFRESH_TOKEN.getMessage()) {
			};
		}
		VerifyResult verifyResult = JwtUtil.validateToken(convertedToken);
		if (verifyResult.isSuccess()) {
			User user = (User) userDetailsService.loadUserByUsername(verifyResult.getUsername());
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),
					user.getPassword());
			return getAuthenticationManager().authenticate(token);
		} else {
			throw new TokenExpiredException("refresh token expired");
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException {
		User user = (User) authResult.getPrincipal();
		UserLoginInfo userLoginInfo = UserLoginInfo.builder().email(user.getEmail())
				.accessToken(JwtUtil.createAccessToken(user))
				.refreshToken(JwtUtil.createRefreshToken(user))
				.build();

		userLoginInfoRepository.save(userLoginInfo);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.getOutputStream().write(objectMapper.writeValueAsBytes(userLoginInfo));
	}
}
