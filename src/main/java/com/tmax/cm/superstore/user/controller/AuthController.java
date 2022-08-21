package com.tmax.cm.superstore.user.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.user.dto.UserLoginRequestDto;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
	@GetMapping("/test")
	public String test(){
		return "hello";
	}
	@GetMapping("/test1")
	public String test1(){
		return "hello";
	}
	@PostMapping("/login")
	public void login(@RequestBody UserLoginRequestDto userLoginRequestDto){}
}
