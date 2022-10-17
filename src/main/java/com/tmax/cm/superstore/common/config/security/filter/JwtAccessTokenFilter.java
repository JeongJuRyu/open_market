package com.tmax.cm.superstore.common.config.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tmax.cm.superstore.common.config.security.JwtUtil;
import com.tmax.cm.superstore.common.config.security.VerifyResult;
import com.tmax.cm.superstore.user.entities.User;

import lombok.RequiredArgsConstructor;

/**
 * access token을 처리하는 필터로서 BasicAuthenticationFilter를 대체한다
 */
@RequiredArgsConstructor
public class JwtAccessTokenFilter extends OncePerRequestFilter {
// public class JwtAccessTokenFilter extends BasicAuthenticationFilter {
	
	private final UserDetailsService userDetailsService;
	private final AuthenticationEntryPoint authenticationEntryPoint;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			String jwt = JwtUtil.resolveToken(request);
			VerifyResult verifiedResult = JwtUtil.validateToken(jwt);
			
			String userName = verifiedResult.getUsername();
			User user = (User) this.userDetailsService.loadUserByUsername(userName);

			// jwt가 유효하다는 것은 이미 인증이 되었다는 뜻이므로 authenticated로 넘긴다
			UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.authenticated(user, null,
					user.getAuthorities());

			// SecurityContextHolder.getContext().setAuthentication(token);
			SecurityContext context = SecurityContextHolder.createEmptyContext();
			context.setAuthentication(token);
			SecurityContextHolder.setContext(context);

		} catch (AuthenticationException exception) {
			// BasicAuthenticationFilter처럼 인증 실패 처리시 SecurityContextHolder를 지운다
			SecurityContextHolder.clearContext();

			// 인증 실패 response 처리한다
			this.authenticationEntryPoint.commence(request, response, exception);

			// 다음 필터로 넘어가지 않고 요청을 끝낸다
			return;
		}

		filterChain.doFilter(request, response);
	}
}
