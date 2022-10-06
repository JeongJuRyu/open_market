package com.tmax.cm.superstore.user.service;

import java.util.UUID;

import javax.security.auth.DestroyFailedException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmax.cm.superstore.user.dto.CreateUserRequestDto;
import com.tmax.cm.superstore.user.dto.CreateUserResponseDto;
import com.tmax.cm.superstore.user.dto.DeleteDeliveryInfoRequestDto;
import com.tmax.cm.superstore.user.dto.EmailAuthRequestDto;
import com.tmax.cm.superstore.user.dto.EmailAuthResponseDto;
import com.tmax.cm.superstore.user.dto.PostDeliveryRequestDto;
import com.tmax.cm.superstore.user.dto.UpdateDeliveryInfoRequestDto;
import com.tmax.cm.superstore.user.dto.UpdateEmailRequestDto;
import com.tmax.cm.superstore.user.dto.UpdateEmailResponseDto;
import com.tmax.cm.superstore.user.dto.UpdatePasswordRequestDto;
import com.tmax.cm.superstore.user.dto.UpdatePasswordResponseDto;
import com.tmax.cm.superstore.user.entities.DeliveryAddress;
import com.tmax.cm.superstore.user.entities.User;
import com.tmax.cm.superstore.user.error.exception.DeliveryAddressNotFoundException;
import com.tmax.cm.superstore.user.error.exception.EmailNotFoundException;
import com.tmax.cm.superstore.user.error.exception.UserAlreadyExistException;
import com.tmax.cm.superstore.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final EmailService emailService;
	private final PasswordEncoder passwordEncoder;

	@Transactional
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
		String email = updateEmailRequestDto.getEmail();
		User user = userRepository.findUserByEmail(email).orElseThrow(EmailNotFoundException::new);
		if(checkEmailDuplicate(updateEmailRequestDto.getEmail())){
			throw new UserAlreadyExistException();
		}

		EmailAuthRequestDto emailAuthRequestDto = EmailAuthRequestDto
			.builder().email(updateEmailRequestDto.getNewEmail()).build();
		EmailAuthResponseDto dto;
		try {
			dto = emailService.authEmail(emailAuthRequestDto);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		user.updateEmail(updateEmailRequestDto.getEmail());
		return UpdateEmailResponseDto.builder()
			.email(updateEmailRequestDto.getEmail())
			.build();
	}

	public EmailAuthResponseDto emailAuth(EmailAuthRequestDto emailAuthRequestDto){
		String email = emailAuthRequestDto.getEmail();
		// 랜덤번호 생성 후 메일 전송
		String valid_num = "1234";
		return EmailAuthResponseDto.builder()
			.validNum(valid_num).build();
	}

	@Transactional
	public UpdatePasswordResponseDto updatePassword(
		UpdatePasswordRequestDto updatePasswordRequestDto){
		User user = userRepository
			.findUserByEmail(updatePasswordRequestDto.getEmail()).orElseThrow(EmailNotFoundException::new);
		String encodedPassword = passwordEncoder
			.encode(updatePasswordRequestDto.getPassword());

		String encodedNewPassword = passwordEncoder
			.encode(updatePasswordRequestDto.getUpdatePassword());
		if(!user.getPassword().equals(encodedPassword)){
			throw new BadCredentialsException("wrong password.");
		}
		user.updatePassword(encodedNewPassword);
		return UpdatePasswordResponseDto.builder().build();
	}

	public boolean checkEmailDuplicate(String email){
		return userRepository.existsByEmail(email);
	}

	@Transactional
	public void postDeliveryInfo(
		PostDeliveryRequestDto dto){
		User user = userRepository
			.findUserByEmail(dto.getEmail()).orElseThrow(EmailNotFoundException::new);
		user.postDeliveryAddress(dto);
	}

	@Transactional
	public void updateDeliveryInfo(UpdateDeliveryInfoRequestDto dto){
		User user = userRepository.findUserByEmail(dto.getEmail())
			.orElseThrow(EmailNotFoundException::new);
		user.updateDeliveryAddress(dto);
	}

	@Transactional
	public void deleteDeliveryInfo(DeleteDeliveryInfoRequestDto dto){
		User user = userRepository.findUserByEmail(dto.getEmail())
			.orElseThrow(EmailNotFoundException::new);
		DeliveryAddress deliveryAddress = user.getDeliveryAddresses().stream()
			.filter(address -> address.getId() == dto.getShippingAddressId())
			.findAny().orElseThrow(DeliveryAddressNotFoundException::new);
		user.getDeliveryAddresses().remove(deliveryAddress);
	}

	@Transactional
	public void setDefaultDelivery(UUID id){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User newUser = userRepository.findUserByEmail(user.getEmail()).orElseThrow(EmailNotFoundException::new);
		newUser.setDeliveryAddress(id);
	}
}
