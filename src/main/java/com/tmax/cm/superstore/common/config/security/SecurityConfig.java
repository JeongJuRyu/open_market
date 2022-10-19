package com.tmax.cm.superstore.common.config.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity//(debug = true)
public class SecurityConfig {
	private final CustomDsl customDsl;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
			// csrf 비활성화
			.csrf().disable()
			// 특정 URL에만 security 활성화
			.requestMatchers(requestMatchers -> requestMatchers.mvcMatchers(
				"/v1/users",
				"/v1/users/delivery/**",
				"/v1/auth/**",
				"/v1/wishlist/**",
				"/v1/review/**",
				"/v1/order/**",
				"/v1/reservation/make",
				"/v1/reservation/list/user",
				"/v1/reservation/{reservationId}/change"))
			/*.authorizeRequests()
			.antMatchers("/v1/users/new").permitAll()
			.antMatchers("/v1/users",
				"/v1/wishlist/**",
				"/v1/review/**",
				"/v1/order/**",
				"/v1/reservation/make",
				"/v1/reservation/list/user",
				"/v1/reservation/{reservationId}/change").authenticated()
			.and()*/
			// 세션 비활성화
			.sessionManagement(sessionManagement -> sessionManagement
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			/*.cors(cors -> cors
				.configurationSource(configurationSource()))*/
			.cors().and()
			.apply(customDsl).and()
			.build();
	}

	/*@Bean
	public CorsConfigurationSource configurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowedOriginPatterns(Arrays.asList("*"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("HEAD","POST","GET","DELETE","PUT"));
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}*/
}
