package com.tmax.cm.superstore.user.service;

import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.security.auth.DestroyFailedException;

import com.tmax.cm.superstore.user.error.exception.UserPhoneNumAlreadyExistException;
import com.tmax.cm.superstore.wishlist.entity.WishlistGroup;
import com.tmax.cm.superstore.wishlist.repository.WishlistGroupRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.user.dto.CreateUserRequestDto;
import com.tmax.cm.superstore.user.dto.EmailAuthRequestDto;
import com.tmax.cm.superstore.user.dto.EmailAuthResponseDto;
import com.tmax.cm.superstore.user.dto.GetUserDeliveryInfoResponseDto;
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
import com.tmax.cm.superstore.user.repository.DeliveryRepository;
import com.tmax.cm.superstore.user.repository.UserRepository;
import com.tmax.cm.superstore.user.util.DeliveryComparator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final EmailService emailService;
	private final DeliveryMapper deliveryMapper;
	private final WishlistGroupRepository wishlistGroupRepository;
	private final DeliveryRepository deliveryRepository;

	@Transactional(readOnly = true)
	public ResponseDto<GetUserInfoResponseDto> getUserInfo(User user){
		user.getDeliveryAddresses().sort(new DeliveryComparator());
		return ResponseDto.<GetUserInfoResponseDto>builder()
				.responseCode(ResponseCode.USER_INFO_READ)
				.data(GetUserInfoResponseDto.builder()
					.name(user.getName())
					.email(user.getEmail())
					.phoneNum(user.getPhoneNum())
					.deliveryAddresses(deliveryMapper.toDeliveriesDto(
						user.getDeliveryAddresses()))
					.build())
				.build();
	}

	@Transactional
	public ResponseDto<Object> createUser(CreateUserRequestDto createUserRequestDto){
		if(checkEmailDuplicate(createUserRequestDto.getEmail())){
			throw new UserAlreadyExistException();
		} else if(checkPhoneNumDuplicate(createUserRequestDto.getUserPhoneNum())){
			throw new UserPhoneNumAlreadyExistException();
		}

		User user = User.builder()
			.email(createUserRequestDto.getEmail())
			.password(createUserRequestDto.getPassword())
			.phoneNum(createUserRequestDto.getUserPhoneNum())
			.address(createUserRequestDto.getAddress())
			.name(createUserRequestDto.getName())
			.build();

		WishlistGroup wishlistGroup = WishlistGroup.builder()
				.position(0)
				.name("?????? ??????")
				.build();

		wishlistGroup.setUpUser(user);
		wishlistGroupRepository.save(wishlistGroup);
		userRepository.save(user);
		return ResponseDto.builder()
			.responseCode(ResponseCode.USER_CREATE)
			.data(null).build();
	}

	@Transactional
	public ResponseDto<Object> deleteUser(User user){
		userRepository.delete(user);
		return ResponseDto.builder()
			.responseCode(ResponseCode.USER_DELETE)
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

	@Transactional(readOnly = true)
	public ResponseDto<GetUserDeliveryInfoResponseDto> getUserDeliveryInfo(User user){
		if(user.getIsDeleted()){
			throw new EmailNotFoundException();
		}
		user.getDeliveryAddresses().sort(new DeliveryComparator());
		return ResponseDto.<GetUserDeliveryInfoResponseDto>builder()
			.responseCode(ResponseCode.USER_DELIVERY_READ)
			.data(GetUserDeliveryInfoResponseDto.builder()
				.deliveryAddresses(deliveryMapper
					.toDeliveriesDto(user.getDeliveryAddresses()))
				.build())
			.build();
	}

	@Transactional
	public ResponseDto<Object> postDeliveryInfo(
		PostDeliveryRequestDto dto, User user){
		if(user.getDeliveryAddresses().size() >= 1 && dto.isDefaultAddress()){
			DeliveryAddress oldDeliveryAddress = user.getDeliveryAddresses().stream()
				.filter(DeliveryAddress::getIsDefaultAddress)
				.findAny().orElseThrow(DeliveryAddressNotFoundException::new);
			oldDeliveryAddress = deliveryRepository.findById(oldDeliveryAddress.getId())
				.orElseThrow(DeliveryAddressNotFoundException::new);
			oldDeliveryAddress.setDefaultAddress(false);
		}
		deliveryRepository.save(user.postDeliveryAddress(dto));
		return ResponseDto.builder()
			.responseCode(ResponseCode.USER_DELIVERY_CREATE)
			.data(null).build();
	}

	@Transactional
	public ResponseDto<Object> updateDeliveryInfo(UpdateDeliveryInfoRequestDto dto){
		DeliveryAddress deliveryAddress = deliveryRepository.findById(dto.getDeliveryAddressId())
			.orElseThrow(DeliveryAddressNotFoundException::new);
		if(dto.getIsDefaultAddress()){
			DeliveryAddress defaultDeliveryAddress = deliveryRepository.findByIsDefaultAddress(true)
				.orElseThrow(DeliveryAddressNotFoundException::new);
			defaultDeliveryAddress.setDefaultAddress(false);
		}
		deliveryAddress.updateDeliveryAddress(dto);
		return ResponseDto.builder()
			.responseCode(ResponseCode.USER_DELIVERY_UPDATE)
			.data(null).build();
	}

	@Transactional
	public ResponseDto<Object> deleteDeliveryInfo(User user, UUID id){
		user.deleteDeliveryAddress(id);
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

	public boolean checkEmailDuplicate(String email){
		return userRepository.existsByEmail(email);
	}

	public Boolean checkPhoneNumDuplicate(String phoneNum){
		return userRepository.existsByPhoneNum(phoneNum);
	}
}
