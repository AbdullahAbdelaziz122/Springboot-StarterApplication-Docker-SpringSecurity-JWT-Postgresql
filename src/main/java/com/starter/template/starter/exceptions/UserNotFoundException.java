package com.starter.template.starter.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(UUID id){
        super("No User Found with Id: "+ id);
    }

    public UserNotFoundException(String email){
        super("No User Found with Id: "+ email);
    }
}
