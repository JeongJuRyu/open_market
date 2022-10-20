package com.tmax.cm.superstore.user.service;

import java.util.Arrays;
import java.util.UUID;

import javax.security.auth.DestroyFailedException;

import com.tmax.cm.superstore.wishlist.entity.WishlistGroup;
import com.tmax.cm.superstore.wishlist.repository.WishlistGroupRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.user.dto.CreateUserRequestDto;
import com.tmax.cm.superstore.user.dto.EmailAuthRequestDto;
import com.tmax.cm.superstore.user.dto.EmailAuthResponseDto;
import com.tmax.cm.superstore.user.dto.GetUserDeliveryInfoResponseDto;
import com.tmax.cm.superstore.user.dto.GetUserInfoRequestDto;
import com.tmax.cm.superstore.user.dto.GetUserInfoResponseDto;
import com.tmax.cm.superstore.user.dto.PostDeliveryRequestDto;
import com.tmax.cm.superstore.user.dto.UpdateDeliveryInfoRequestDto;
import com.tmax.cm.superstore.user.dto.UpdateEmailRequestDto;
import com.tmax.cm.superstore.user.dto.UpdateEmailResponseDto;
import com.tmax.cm.superstore.user.dto.UpdatePasswordRequestDto;
import com.tmax.cm.superstore.user.entities.DeliveryAddress;
import com.tmax.cm.superstore.user.entities.User;
import com.tmax.cm.superstore.user.error.exception.DeliveryAddressNotFoundException;
import com.tmax.cm.superstore.user.error.exception.EmailNotFoundException;
import com.tmax.cm.superstore.user.error.exception.UserAlreadyExistException;
import com.tmax.cm.superstore.user.error.exception.WrongPasswordException;
import com.tmax.cm.superstore.user.mapper.DeliveryMapper;
import com.tmax.cm.superstore.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final EmailService emailService;
	private final DeliveryMapper deliveryMapper;
	private final WishlistGroupRepository wishlistGroupRepository;

	@Transactional(readOnly = true)
	public ResponseDto<GetUserInfoResponseDto> getUserInfo(GetUserInfoRequestDto dto){
		User user = userRepository.findUserByEmail(dto.getEmail()).orElseThrow(EmailNotFoundException::new);
		return ResponseDto.<GetUserInfoResponseDto>builder()
				.responseCode(ResponseCode.USER_INFO_READ)
				.data(GetUserInfoResponseDto.builder()
					.name(user.getName())
					.email(user.getEmail())
					.phoneNum(user.getPhoneNum())
					.build())
				.build();
	}

	@Transactional
	public ResponseDto<Object> createUser(CreateUserRequestDto createUserRequestDto){
		if(checkEmailDuplicate(createUserRequestDto.getEmail())){
			throw new UserAlreadyExistException();
		}

		WishlistGroup wishlistGroup = WishlistGroup.builder()
				.position(0)
				.name("기본 그룹")
				.build();

		User user = User.builder()
			.email(createUserRequestDto.getEmail())
			.password(createUserRequestDto.getPassword())
			.phoneNum(createUserRequestDto.getUserPhoneNum())
			.address(createUserRequestDto.getAddress())
			.name(createUserRequestDto.getName())
			.build();

		wishlistGroup.setUpUser(user);

		wishlistGroupRepository.save(wishlistGroup);
		userRepository.save(user);
		return ResponseDto.builder()
			.responseCode(ResponseCode.USER_CREATE)
			.data(null).build();
	}
	@Transactional
	public ResponseDto<UpdateEmailResponseDto> updateEmail(UpdateEmailRequestDto updateEmailRequestDto){
		String email = updateEmailRequestDto.getEmail();
		User user = userRepository.findUserByEmail(email).orElseThrow(EmailNotFoundException::new);
		if(checkEmailDuplicate(updateEmailRequestDto.getNewEmail())){
			throw new UserAlreadyExistException();
		}
		user.updateEmail(updateEmailRequestDto.getNewEmail());
		return ResponseDto.<UpdateEmailResponseDto>builder()
			.responseCode(ResponseCode.USER_EMAIL_UPDATE)
			.data(UpdateEmailResponseDto.builder()
				.email(updateEmailRequestDto.getNewEmail())
				.build())
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
	public void updatePassword(
		UpdatePasswordRequestDto updatePasswordRequestDto){
		User user = userRepository
			.findUserByEmail(updatePasswordRequestDto.getEmail()).orElseThrow(EmailNotFoundException::new);
		String password = updatePasswordRequestDto.getPassword();

		String newPassword = updatePasswordRequestDto.getNewPassword();
		if(!user.getPassword().equals(password)){
			throw new WrongPasswordException();
		}
		user.updatePassword(newPassword);
	}

	public boolean checkEmailDuplicate(String email){
		return userRepository.existsByEmail(email);
	}

	@Transactional(readOnly = true)
	public ResponseDto<GetUserDeliveryInfoResponseDto> getUserDeliveryInfo(){
		String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository
			.findUserByEmail(email).orElseThrow(EmailNotFoundException::new);
		return ResponseDto.<GetUserDeliveryInfoResponseDto>builder()
			.responseCode(ResponseCode.USER_DELIVERY_READ)
			.data(GetUserDeliveryInfoResponseDto.builder()
				.deliveryAddresses(deliveryMapper.toDeliveriesDto(user.getDeliveryAddresses()))
				.build())
			.build();
	}
	@Transactional
	public ResponseDto<Object> postDeliveryInfo(
		PostDeliveryRequestDto dto){
		User user = userRepository
			.findUserByEmail(dto.getEmail()).orElseThrow(EmailNotFoundException::new);
		user.postDeliveryAddress(dto);
		return ResponseDto.builder()
			.responseCode(ResponseCode.USER_DELIVERY_CREATE)
			.data(null).build();
	}

	@Transactional
	public ResponseDto<Object> updateDeliveryInfo(UpdateDeliveryInfoRequestDto dto){
		User user = userRepository.findUserByEmail(dto.getEmail())
			.orElseThrow(EmailNotFoundException::new);
		user.updateDeliveryAddress(dto);
		return ResponseDto.builder()
			.responseCode(ResponseCode.USER_DELIVERY_UPDATE)
			.data(null).build();
	}

	@Transactional
	public ResponseDto<Object> deleteDeliveryInfo(UUID id){
		String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository
			.findUserByEmail(email).orElseThrow(EmailNotFoundException::new);
		DeliveryAddress deliveryAddress = user.getDeliveryAddresses().stream()
			.filter(address -> address.getId() == id)
			.findAny().orElseThrow(DeliveryAddressNotFoundException::new);
		user.getDeliveryAddresses().remove(deliveryAddress);
		return ResponseDto.builder()
			.responseCode(ResponseCode.USER_DELIVERY_DELETE)
			.data(null).build();
	}

	@Transactional
	public ResponseDto<Object> setDefaultDelivery(UUID id){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User newUser = userRepository.findUserByEmail(user.getEmail()).orElseThrow(EmailNotFoundException::new);
		newUser.setDeliveryAddress(id);
		return ResponseDto.builder()
			.responseCode(ResponseCode.USER_DEFAULT_DELIVERY_SET)
			.data(null).build();
	}
}
