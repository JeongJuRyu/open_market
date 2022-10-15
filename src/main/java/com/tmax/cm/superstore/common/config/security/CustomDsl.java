package com.tmax.cm.superstore.common.config.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.tmax.cm.superstore.common.config.security.filter.JwtAccessTokenFilter;
import com.tmax.cm.superstore.common.config.security.filter.JwtLoginFilter;
import com.tmax.cm.superstore.common.config.security.filter.JwtRefreshTokenFilter;
import com.tmax.cm.superstore.user.repository.UserLoginInfoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {

	private final UserLoginInfoRepository userLoginInfoRepository;
	private final UserDetailsService userDetailsService;
	private final AuthenticationEntryPoint authenticationEntryPoint;

	@Override
	public void configure(HttpSecurity http) {
		AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
		JwtAccessTokenFilter jwtAccessTokenFilter = new JwtAccessTokenFilter(this.userDetailsService,
				this.authenticationEntryPoint);
		JwtLoginFilter jwtLoginFilter = new JwtLoginFilter(authenticationManager, this.userLoginInfoRepository);
		JwtRefreshTokenFilter jwtRefreshTokenFilter = new JwtRefreshTokenFilter(authenticationManager,
				this.userDetailsService, this.userLoginInfoRepository);

		http.addFilterAt(jwtAccessTokenFilter, BasicAuthenticationFilter.class)
				.addFilterAt(jwtLoginFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilterAfter(jwtRefreshTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
