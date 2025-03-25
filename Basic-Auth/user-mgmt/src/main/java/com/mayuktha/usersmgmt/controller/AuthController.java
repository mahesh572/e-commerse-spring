package com.mayuktha.usersmgmt.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayuktha.usersmgmt.dto.LoginRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	@PostMapping("/login")
	public void loginuser(@RequestBody LoginRequest loginRequest) {
		
	}
	
	
	
	// @PostMapping("/register")
}
