package com.example.apisecuritymanager.security;


import com.example.apisecuritymanager.dto.LoginRequest;
import com.example.apisecuritymanager.dto.LoginResponse;
import com.example.apisecuritymanager.dto.SignUpRequest;
import com.example.apisecuritymanager.security.model.User;
import com.example.apisecuritymanager.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public LoginResponse authenticateRequest(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtUtils.generateJwtToken(auth);
        UserDetailsImpl details = (UserDetailsImpl) auth.getPrincipal();

        return LoginResponse.builder()
                .jwt(jwt)
                .email(details.getEmail())
                .build();
    }

    public String signUpUser(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Username " + request.getEmail() + " already exist");
        }
        User user = User.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        return "User was successfully created";
    }
}
