package com.starter.template.starter.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String email){
        super("User already exits with email: "+ email);
    }
}
