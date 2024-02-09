package com.example.zart.controller;

import com.example.zart.dto.auth.AuthRegisterRequest;
import com.example.zart.dto.auth.AuthRegisterResponse;
import com.example.zart.dto.auth.LoginRequest;
import com.example.zart.dto.auth.LoginResponse;
import com.example.zart.entity.User;
import com.example.zart.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse loginUser(@Valid @RequestBody LoginRequest loginBody) {
        return this.authService.loginUser(loginBody);
    }

    @PostMapping()
    public ResponseEntity<AuthRegisterResponse> registerUser(@Valid @RequestBody AuthRegisterRequest userBody) {
            return this.authService.registerUser(userBody);
    }
}
