package com.tmax.cm.superstore.common.config.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.tmax.cm.superstore.common.config.security.handler.JwtAccessDeniedHandler;
import com.tmax.cm.superstore.common.config.security.handler.JwtAuthenticationEntryPoint;

@EnableWebSecurity//(debug = true)
public class SecurityConfig {
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	private final CustomDsl customDsl;
	public SecurityConfig(
		JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
		JwtAccessDeniedHandler jwtAccessDeniedHandler,
		CustomDsl customDsl) {
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
		this.customDsl = customDsl;
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		//JwtLoginFilter jwtLoginFilter = new JwtLoginFilter(authen)
		return httpSecurity
			.csrf().disable()

			.authorizeRequests()
			.antMatchers("/v1/auth/test").authenticated()
			.anyRequest().permitAll()
			//.exceptionHandling()
			//.authenticationEntryPoint(jwtAuthenticationEntryPoint)
			//.accessDeniedHandler(jwtAccessDeniedHandler)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.cors().configurationSource(configurationSource())

			.and()
			.apply(customDsl)
			.and()
			//.apply(new JwtSecurityConfig(tokenProvider))
			.build();
	}

	@Bean
	public WebSecurityCustomizer configure(){
		return (web) -> {
			web.ignoring()
				.antMatchers("h2-console/**");
		};
	}

	@Bean
	public CorsConfigurationSource configurationSource(){
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowedOriginPatterns(List.of("*"));
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
