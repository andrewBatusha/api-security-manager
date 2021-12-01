package com.example.apisecuritymanager.controller;

import com.example.apisecuritymanager.dto.JwtResponse;
import com.example.apisecuritymanager.dto.LoginRequest;
import com.example.apisecuritymanager.dto.LoginResponse;
import com.example.apisecuritymanager.dto.SignUpRequest;
import com.example.apisecuritymanager.security.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Api(tags = "Authentication API")
public class AuthController {
    private final AuthService service;

    @PostMapping("/sign-in")
    @ApiOperation(value = "Get credentials  for login")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.authenticateRequest(request));
    }

    @PostMapping("/sign-up")
    @ApiOperation(value = "Get credentials for registration")
    public ResponseEntity<String> createUser(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.ok(service.signUpUser(request));
    }

    @ApiOperation(value = "Security pass")
    @PostMapping("/pass")
    public ResponseEntity<JwtResponse> securityPass(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(new JwtResponse());
    }

}
