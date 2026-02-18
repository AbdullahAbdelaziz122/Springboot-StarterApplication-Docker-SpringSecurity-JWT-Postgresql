package com.starter.template.starter.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.starter.template.starter.models.User;
import com.starter.template.starter.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService{

    private final UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not Found with email: "+email));
        return new SecurityUser(user);
    }
    
}
