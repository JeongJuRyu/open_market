/*package com.tmax.cm.superstore.common.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.mapstruct.ap.shaded.freemarker.template.utility.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

public class JwtFilter extends GenericFilterBean {
	private final Logger logger = LoggerFactory.getLogger(JwtFilter.class);


	private TokenProvider tokenProvider;

	public JwtFilter(TokenProvider tokenProvider){
		this.tokenProvider = tokenProvider;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
		IOException,
		ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		String jwt = JWTUtil.resolveToken(httpServletRequest);
		String requestURI = httpServletRequest.getRequestURI();
		// authentication을 jwt로부터 얻어내고, 그것을 SecurityContextHolder의 context의 authentication에 저장한다.
		if(StringUtils.hasText(jwt) && JWTUtil.validateToken(jwt)){
			Authentication authentication = tokenProvider.getAuthentication(jwt);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			logger.debug("Security Context에 '{}' 인증 정보를 저장했습니다. uri: {}", authentication.getName(), requestURI);
		} else {
			logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
		}
		chain.doFilter(request, response);
	}


}
*/