package com.starter.template.starter.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface IAuthenticationService {
    UserDetails authenticate (String email, String password);
    UserDetails validateToken(String token);
}
