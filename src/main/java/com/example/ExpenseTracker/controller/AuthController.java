package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.DTO.AuthResponse;
import com.example.ExpenseTracker.model.DTO.LoginRequest;
import com.example.ExpenseTracker.model.DTO.SignupRequest;
import com.example.ExpenseTracker.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public void signup(@RequestBody SignupRequest request) {
        authService.signup(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
