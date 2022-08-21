package com.tmax.cm.superstore.common.config.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.tmax.cm.superstore.user.repository.UserLoginInfoRepository;
// import com.tmax.cm.superstore.user.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {
	private final UserLoginInfoRepository userLoginInfoRepository;
	// private final CustomUserDetailsService userDetailsService;

	@Override
	public void configure(HttpSecurity http) {
		AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
		http.addFilterBefore(new JwtLoginFilter(authenticationManager, userLoginInfoRepository),
				UsernamePasswordAuthenticationFilter.class)
			.addFilterAfter(new JWTAccessTokenFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);

	}
}
