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

import com.tmax.cm.superstore.user.dto.CreateUserRequestDto;
import com.tmax.cm.superstore.user.dto.CreateUserResponseDto;
import com.tmax.cm.superstore.user.dto.DeleteDeliveryInfoRequestDto;
import com.tmax.cm.superstore.user.dto.GetUserInfoRequestDto;
import com.tmax.cm.superstore.user.dto.GetUserInfoResponseDto;
import com.tmax.cm.superstore.user.dto.PostDeliveryRequestDto;
import com.tmax.cm.superstore.user.dto.UpdateDeliveryInfoRequestDto;
import com.tmax.cm.superstore.user.dto.UpdateEmailRequestDto;
import com.tmax.cm.superstore.user.dto.UpdateEmailResponseDto;
import com.tmax.cm.superstore.user.dto.UpdatePasswordRequestDto;
import com.tmax.cm.superstore.user.dto.UpdatePasswordResponseDto;
import com.tmax.cm.superstore.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
	private final UserService userService;
	@GetMapping
	public ResponseEntity<GetUserInfoResponseDto> getUser(@RequestBody GetUserInfoRequestDto getUserInfoRequestDto) {
		return ResponseEntity.ok().body(userService.getUserInfo(getUserInfoRequestDto));
	}

	@PostMapping
	public ResponseEntity<CreateUserResponseDto> createUser(
		@Valid @RequestBody CreateUserRequestDto createUserRequestDto){
		return ResponseEntity.ok().body(userService.createUser(createUserRequestDto));
	}

	@PatchMapping("/email")
	public ResponseEntity<UpdateEmailResponseDto> updateEmail(
		@RequestBody UpdateEmailRequestDto updateEmailRequestDto){
		return ResponseEntity.ok().body(userService.updateEmail(updateEmailRequestDto));
	}

	@PatchMapping("/password")
	public ResponseEntity<Object> updatePassword(@RequestBody
		UpdatePasswordRequestDto updatePasswordRequestDto){
		userService.updatePassword(updatePasswordRequestDto);
		return ResponseEntity.ok().body(null);
	}

	@PostMapping("/delivery")
	public ResponseEntity<Object> postDeliveryInfo(
		@RequestBody PostDeliveryRequestDto postDeliveryInfo){
		userService.postDeliveryInfo(postDeliveryInfo);
		return ResponseEntity.ok().body(null);
	}

	@PatchMapping("/delivery/{id}")
	public ResponseEntity<Object> updateDeliveryInfo(
		@RequestBody UpdateDeliveryInfoRequestDto updateDeliveryInfoRequestDto){
		userService.updateDeliveryInfo(updateDeliveryInfoRequestDto);
		return ResponseEntity.ok().body(null);
	}


	@DeleteMapping("/delivery/{id}")
	public ResponseEntity<Object> deleteDeliveryInfo(@RequestBody DeleteDeliveryInfoRequestDto deleteDeliveryInfoRequestDto){
		userService.deleteDeliveryInfo(deleteDeliveryInfoRequestDto);
		return ResponseEntity.ok().body(null);
	}

	@PostMapping("/delivery/{id}")
	public ResponseEntity<Object> setDefaultADelivery(@PathVariable UUID id){
		userService.setDefaultDelivery(id);
		return ResponseEntity.ok().body(null);
	}
}
