package com.starter.template.starter.services;

import org.springframework.security.core.userdetails.UserDetails;

import com.starter.template.starter.dtos.AuthResponse;
import com.starter.template.starter.dtos.LoginRequest;
import com.starter.template.starter.dtos.RegisterRequest;
import com.starter.template.starter.dtos.RegisterResponse;

public interface IAuthenticationService {
    UserDetails authenticate (String email, String password);
    UserDetails validateToken(String token);
    String generateToken(UserDetails userDetails);
    AuthResponse login(LoginRequest request);
    RegisterResponse register (RegisterRequest request);
}
