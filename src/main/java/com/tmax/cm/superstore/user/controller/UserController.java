package com.tmax.cm.superstore.user.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.user.dto.CreateUserRequestDto;
import com.tmax.cm.superstore.user.dto.GetUserDeliveryInfoResponseDto;
import com.tmax.cm.superstore.user.dto.GetUserInfoRequestDto;
import com.tmax.cm.superstore.user.dto.GetUserInfoResponseDto;
import com.tmax.cm.superstore.user.dto.PostDeliveryRequestDto;
import com.tmax.cm.superstore.user.dto.UpdateDeliveryInfoRequestDto;
import com.tmax.cm.superstore.user.dto.UpdateEmailRequestDto;
import com.tmax.cm.superstore.user.dto.UpdateEmailResponseDto;
import com.tmax.cm.superstore.user.dto.UpdatePasswordRequestDto;
import com.tmax.cm.superstore.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
	private final UserService userService;
	@GetMapping
	public ResponseEntity<ResponseDto<GetUserInfoResponseDto>> getUser(@RequestBody GetUserInfoRequestDto getUserInfoRequestDto) {
		return ResponseEntity.ok().body(userService.getUserInfo(getUserInfoRequestDto));
	}

	@PostMapping
	public ResponseEntity<ResponseDto<Object>> createUser(
		@Valid @RequestBody CreateUserRequestDto createUserRequestDto){
		return ResponseEntity.ok()
			.body(userService.createUser(createUserRequestDto));
	}

	@PatchMapping("/email")
	public ResponseEntity<ResponseDto<UpdateEmailResponseDto>> updateEmail(
		@RequestBody UpdateEmailRequestDto updateEmailRequestDto){
		return ResponseEntity.ok()
			.body(userService.updateEmail(updateEmailRequestDto));
	}

	@PatchMapping("/password")
	public ResponseEntity<ResponseDto<Object>> updatePassword(@RequestBody
		UpdatePasswordRequestDto updatePasswordRequestDto){
		userService.updatePassword(updatePasswordRequestDto);
		return ResponseEntity.ok()
			.body(ResponseDto.builder().responseCode(ResponseCode.USER_PASSWORD_UPDATE).data(null).build());
	}

	@GetMapping("/delivery")
	public ResponseEntity<ResponseDto<GetUserDeliveryInfoResponseDto>> getDeliveryInfo(){
		return ResponseEntity.ok().body(userService.getUserDeliveryInfo());
	}

	@PostMapping("/delivery")
	public ResponseEntity<ResponseDto<Object>> postDeliveryInfo(
		@RequestBody PostDeliveryRequestDto postDeliveryInfo){
		return ResponseEntity.ok().body(userService.postDeliveryInfo(postDeliveryInfo));
	}

	@PatchMapping("/delivery/{id}")
	public ResponseEntity<ResponseDto<Object>> updateDeliveryInfo(
		@RequestBody UpdateDeliveryInfoRequestDto updateDeliveryInfoRequestDto){
		return ResponseEntity.ok().body(userService.updateDeliveryInfo(updateDeliveryInfoRequestDto));
	}


	@DeleteMapping("/delivery/{id}")
	public ResponseEntity<ResponseDto<Object>> deleteDeliveryInfo(@PathVariable UUID id){
		return ResponseEntity.ok().body(userService.deleteDeliveryInfo(id));
	}

	@PostMapping("/delivery/{id}")
	public ResponseEntity<ResponseDto<Object>> setDefaultADelivery(@PathVariable UUID id){
		return ResponseEntity.ok().body(userService.setDefaultDelivery(id));
	}
}
