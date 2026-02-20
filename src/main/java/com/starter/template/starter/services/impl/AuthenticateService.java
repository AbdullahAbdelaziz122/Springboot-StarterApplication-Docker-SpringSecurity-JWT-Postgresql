package com.starter.template.starter.services.impl;

import java.security.Key;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.starter.template.starter.dtos.AuthResponse;
import com.starter.template.starter.dtos.LoginRequest;
import com.starter.template.starter.dtos.RegisterRequest;
import com.starter.template.starter.dtos.RegisterResponse;
import com.starter.template.starter.exceptions.UserAlreadyExistsException;
import com.starter.template.starter.models.Role;
import com.starter.template.starter.models.User;
import com.starter.template.starter.repositories.UserRepository;
import com.starter.template.starter.services.IAuthenticationService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticateService implements IAuthenticationService{

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.expiration}")
	private Long jwtExpiry;

    @Override
    public UserDetails authenticate(String email, String password) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
        );
        return userDetailsService.loadUserByUsername(email);
    }


    @Override
    public UserDetails validateToken(String token) {
        String username = extractUsername(token);
        return userDetailsService.loadUserByUsername(username);
    }

    private String extractUsername(String token) {
        Claims claims = Jwts.parserBuilder()
        .setSigningKey(getSignKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
        return claims.getSubject();
    }

    private Key getSignKey(){
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }


    @Override
    public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpiry))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();

	}


    @Override
    public AuthResponse login(LoginRequest request) {
        UserDetails userDetails = authenticate(request.getEmail(), request.getPassword());

        String tokenValue = generateToken(userDetails);

        AuthResponse response = AuthResponse.builder()
        .token(tokenValue)
        .expiresIn(jwtExpiry.toString())
        .build();
        log.info("User Login success: "+ userDetails.getUsername());

        return response;
    }


    @Override
    public RegisterResponse register(RegisterRequest request) {
    
        if(checkUserAlreadyExist(request.getEmail())){
            throw new UserAlreadyExistsException("User with this email Already exists");
        }
        User newUser = User.builder()
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.firstname(request.getFirstname())
				.lastname(request.getLastname())
				.createdAt(LocalDateTime.now())
				.role(Role.USER).build();

		User savedUser = userRepository.save(newUser);
		return RegisterResponse.builder()
				.id(savedUser.getId())
				.email(savedUser.getEmail())
				.message("User registered successfully")
				.build();
	}
    


    boolean checkUserAlreadyExist(String email){
        return userRepository.existsByEmail(email);
    }



}
