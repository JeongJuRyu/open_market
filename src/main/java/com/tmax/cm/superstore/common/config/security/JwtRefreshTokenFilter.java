/*package com.tmax.cm.superstore.common.config.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmax.cm.superstore.user.dto.TokenRequestDto;
import com.tmax.cm.superstore.user.entities.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtRefreshTokenFilter extends UsernamePasswordAuthenticationFilter {
	private ObjectMapper objectMapper = new ObjectMapper();
	private boolean postOnly = true;
	private final UserDetailsService userDetailsService;

	public JwtRefreshTokenFilter(AuthenticationManager authenticationManager , UserDetailsService userDetailsService){
		super(authenticationManager);
		this.userDetailsService = userDetailsService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
		AuthenticationException {
		if(this.postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		TokenRequestDto tokenRequestDto;
		try{
			tokenRequestDto = objectMapper.readValue(request.getInputStream(), TokenRequestDto.class);
		} catch (IOException e) {
			throw new AuthenticationException("Need Credential Information") {
			};
		}
		String jwtToken = JWTUtil.resolveToken(request);
		if(tokenRequestDto.getRefreshToken() == null && jwtToken == null){
			log.error("Need Access Token");
			throw new AuthenticationException("Need Access Token") {
			};
		}
		if(tokenRequestDto.getRefreshToken() == null){
			log.error("Need Refresh Token");
			throw new AuthenticationException("Need Refresh Token") {
			};
		}
		String forDeleteAccessToken = jwtToken;
		if(forDeleteAccessToken == null){
			forDeleteAccessToken = tokenRequestDto.getAccessToken();
		}

		DecodedJWT expiredAccessToken = JWT.decode(forDeleteAccessToken);

		User user = (User)userDetailsService.loadUserByUsername(expiredAccessToken.getSubject());
		boolean verifyResult = JWTUtil.validateToken(tokenRequestDto.getRefreshToken());

	}
}
 */
