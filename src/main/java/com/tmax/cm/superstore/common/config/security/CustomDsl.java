package com.tmax.cm.superstore.common.config.security;

import com.tmax.cm.superstore.common.config.security.filter.JWTAccessTokenFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.tmax.cm.superstore.common.config.security.filter.JWTAccessTokenFilter;
import com.tmax.cm.superstore.common.config.security.filter.JwtLoginFilter;
import com.tmax.cm.superstore.common.config.security.filter.JwtRefreshTokenFilter;
import com.tmax.cm.superstore.user.repository.UserLoginInfoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {
	private final UserLoginInfoRepository userLoginInfoRepository;
	private final UserDetailsService userDetailsService;

	@Override
	public void configure(HttpSecurity http) {
		AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
		http.addFilterBefore(new JwtLoginFilter(authenticationManager, userLoginInfoRepository),
				UsernamePasswordAuthenticationFilter.class)
			.addFilterAfter(new JWTAccessTokenFilter(authenticationManager, userDetailsService, userLoginInfoRepository), UsernamePasswordAuthenticationFilter.class)
			.addFilterAt(new JwtRefreshTokenFilter(authenticationManager, userDetailsService, userLoginInfoRepository), UsernamePasswordAuthenticationFilter.class);

	}
}
