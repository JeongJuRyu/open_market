package com.tmax.cm.superstore.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmax.cm.superstore.user.dto.CreateUserRequestDto;
import com.tmax.cm.superstore.user.dto.CreateUserResponseDto;
import com.tmax.cm.superstore.user.dto.EmailAuthRequestDto;
import com.tmax.cm.superstore.user.dto.EmailAuthResponseDto;
import com.tmax.cm.superstore.user.dto.UpdateDeliveryRequestDto;
import com.tmax.cm.superstore.user.dto.UpdateDeliveryResponseDto;
import com.tmax.cm.superstore.user.dto.UpdateEmailRequestDto;
import com.tmax.cm.superstore.user.dto.UpdateEmailResponseDto;
import com.tmax.cm.superstore.user.dto.UpdatePasswordRequestDto;
import com.tmax.cm.superstore.user.dto.UpdatePasswordResponseDto;
import com.tmax.cm.superstore.user.entities.User;
import com.tmax.cm.superstore.user.error.exception.EmailNotFoundException;
import com.tmax.cm.superstore.user.error.exception.UserAlreadyExistException;
import com.tmax.cm.superstore.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public CreateUserResponseDto createUser(CreateUserRequestDto createUserRequestDto){
		if(checkEmailDuplicate(createUserRequestDto.getEmail())){
			throw new UserAlreadyExistException();
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
	@Transactional
	public UpdateEmailResponseDto updateEmail(UpdateEmailRequestDto updateEmailRequestDto){
		User user = userRepository.findUserByEmail(updateEmailRequestDto.getEmail()).orElseThrow(EmailNotFoundException::new);
		if(checkEmailDuplicate(updateEmailRequestDto.getEmail())){
			throw new UserAlreadyExistException();
		}
		user.updateEmail(updateEmailRequestDto.getEmail());
		return null;
	}

	public EmailAuthResponseDto emailAuth(EmailAuthRequestDto emailAuthRequestDto){
		String email = emailAuthRequestDto.getEmail();
		// 랜덤번호 생성 후 메일 전송
		String valid_num = "1234";
		return EmailAuthResponseDto.builder()
			.valid_num(valid_num).build();
	}

	@Transactional
	public UpdatePasswordResponseDto updatePassword(
		UpdatePasswordRequestDto updatePasswordRequestDto){
		User user = userRepository
			.findUserByEmail(updatePasswordRequestDto.getEmail()).orElseThrow(EmailNotFoundException::new);
		String encodedPassword = passwordEncoder
			.encode(updatePasswordRequestDto.getUpdatePassword());
		user.updatePassword(encodedPassword);
		return UpdatePasswordResponseDto.builder().build();
	}

	public boolean checkEmailDuplicate(String email){
		return userRepository.existsByEmail(email);
	}

	@Transactional
	public UpdateDeliveryResponseDto updateDeliveryInfo(
		UpdateDeliveryRequestDto updateDeliveryRequestDto){
		User user = userRepository
			.findUserByEmail(updateDeliveryRequestDto.getEmail()).orElseThrow(EmailNotFoundException::new);
		user.updateDeliveryAddress(updateDeliveryRequestDto);
		return UpdateDeliveryResponseDto.builder().build();
	}
}
