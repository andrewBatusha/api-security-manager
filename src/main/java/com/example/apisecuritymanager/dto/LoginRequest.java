package com.example.apisecuritymanager.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
