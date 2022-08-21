package com.tmax.cm.superstore.user.service;

import static org.springframework.security.core.userdetails.User.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.user.dto.CreateUserRequestDto;
import com.tmax.cm.superstore.user.dto.CreateUserResponseDto;
import com.tmax.cm.superstore.user.entities.User;
import com.tmax.cm.superstore.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public CreateUserResponseDto createUser(CreateUserRequestDto createUserRequestDto){
		if(checkEmailDuplicate(createUserRequestDto.getEmail())){
			//throw new UserAlreadyExistException();
		}
		User user = User.builder()
			.email(createUserRequestDto.getEmail())
			.password(passwordEncoder.encode(createUserRequestDto.getPassword()))
			.phoneNum(createUserRequestDto.getUserPhoneNum())
			.address(createUserRequestDto.getAddress())
			.build();

		userRepository.save(user);
		return null;
	}

	public boolean checkEmailDuplicate(String email){
		return userRepository.existsByEmail(email);
	}
}
