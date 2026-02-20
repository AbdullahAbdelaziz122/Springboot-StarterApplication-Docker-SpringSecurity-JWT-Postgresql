package com.starter.template.starter.controllers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starter.template.starter.dtos.ApiResponse;
import com.starter.template.starter.dtos.AuthResponse;
import com.starter.template.starter.dtos.LoginRequest;
import com.starter.template.starter.dtos.RegisterRequest;
import com.starter.template.starter.dtos.RegisterResponse;
import com.starter.template.starter.services.IAuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthenticationService authenticationService;

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginRequest request) {
		AuthResponse authResponse = authenticationService.login(request);
			
		ApiResponse<AuthResponse> response = ApiResponse.<AuthResponse>builder()
				.success(true)
				.message("Login successful")
				.data(authResponse)
				.timestamp(LocalDateTime.now())
				.build();

		return new ResponseEntity<ApiResponse<AuthResponse>>(response, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<RegisterResponse>> register(@RequestBody RegisterRequest registerRequest) {
		RegisterResponse registerResponse = authenticationService.register(registerRequest);
		ApiResponse<RegisterResponse> response = ApiResponse.<RegisterResponse>builder()
				.success(true)
				.message("Register successful")
				.data(registerResponse)
				.timestamp(LocalDateTime.now())
				.build();
		return new ResponseEntity<ApiResponse<RegisterResponse>>(response, HttpStatus.CREATED);
	}

}
