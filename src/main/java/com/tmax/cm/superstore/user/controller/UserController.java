package com.tmax.cm.superstore.user.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.user.dto.CreateUserRequestDto;
import com.tmax.cm.superstore.user.dto.CreateUserResponseDto;
import com.tmax.cm.superstore.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
	private final UserService userService;

	@PostMapping
	public ResponseEntity<CreateUserResponseDto> createUser(
		@Valid @RequestBody CreateUserRequestDto createUserRequestDto){
		return ResponseEntity.ok().body(userService.createUser(createUserRequestDto));
	}

}
