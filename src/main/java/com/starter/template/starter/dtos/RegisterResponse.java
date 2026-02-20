package com.starter.template.starter.dtos;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponse {
    
    private UUID id;         
    private String email;
    private String message;

}