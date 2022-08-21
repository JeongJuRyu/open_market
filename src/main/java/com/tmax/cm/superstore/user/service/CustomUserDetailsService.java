// package com.tmax.cm.superstore.user.service;

// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import com.tmax.cm.superstore.user.entities.User;
// import com.tmax.cm.superstore.user.repository.UserRepository;

// import lombok.RequiredArgsConstructor;

// @RequiredArgsConstructor
// @Service
// public class CustomUserDetailsService implements UserDetailsService {
// 	private final UserRepository userRepository;
// 	@Override
// 	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
// 		User user = userRepository.findUserByEmail(username)
// 			.orElseThrow(()->new UsernameNotFoundException("데이터베이스에 존재하지 않습니다."));
// 		return user;
// 	}
// }
