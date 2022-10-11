package com.tmax.cm.superstore.common.config.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.tmax.cm.superstore.common.config.security.JwtUtil;
import com.tmax.cm.superstore.common.config.security.VerifyResult;
import com.tmax.cm.superstore.error.lib.AuthErrorCode;
import com.tmax.cm.superstore.user.repository.UserLoginInfoRepository;
import com.tmax.cm.superstore.user.service.UserDetailsServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTAccessTokenFilter extends BasicAuthenticationFilter {
	public JWTAccessTokenFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws
		IOException,
		ServletException {
		String JwtToken = JwtUtil.resolveToken(request);
		VerifyResult verifiedResult = JwtUtil.validateToken(JwtToken);
		String userName = verifiedResult.getUsername();
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
			userName, null, null
		);
		SecurityContextHolder.getContext().setAuthentication(token);
		chain.doFilter(request,response);
	}
}
