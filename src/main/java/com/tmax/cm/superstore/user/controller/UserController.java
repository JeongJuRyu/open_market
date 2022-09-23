package com.tmax.cm.superstore.user.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.user.dto.CreateUserRequestDto;
import com.tmax.cm.superstore.user.dto.CreateUserResponseDto;
import com.tmax.cm.superstore.user.dto.UpdateDeliveryRequestDto;
import com.tmax.cm.superstore.user.dto.UpdateDeliveryResponseDto;
import com.tmax.cm.superstore.user.dto.UpdateEmailRequestDto;
import com.tmax.cm.superstore.user.dto.UpdateEmailResponseDto;
import com.tmax.cm.superstore.user.dto.UpdatePasswordRequestDto;
import com.tmax.cm.superstore.user.dto.UpdatePasswordResponseDto;
import com.tmax.cm.superstore.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
	private final UserService userService;

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
	public ResponseEntity<UpdatePasswordResponseDto> updatePassword(@RequestBody
		UpdatePasswordRequestDto updatePasswordRequestDto){
		return ResponseEntity.ok().body(userService.updatePassword(updatePasswordRequestDto));
	}

	@PostMapping("/delivery")
	public ResponseEntity<UpdateDeliveryResponseDto> updateDeliveryInfo(
		@RequestBody UpdateDeliveryRequestDto updateDeliveryRequestDto){
		return ResponseEntity.ok().body(userService.updateDeliveryInfo(updateDeliveryRequestDto));
	}
}
