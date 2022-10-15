package com.tmax.cm.superstore.common.config.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.tmax.cm.superstore.user.entities.User;
import com.tmax.cm.superstore.user.error.exception.WrongPasswordException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	private final UserDetailsService userDetailsService;
	// private final PasswordEncoder bCryptPasswordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

		String userEmail = token.getName();
		User user = (User) userDetailsService.loadUserByUsername(userEmail);

		CharSequence userPw = (String) token.getCredentials();
		if (!userPw.equals(user.getPassword())) {
			throw new WrongPasswordException();
		}

		return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
