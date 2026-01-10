package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.DTO.AuthResponse;
import com.example.ExpenseTracker.model.DTO.LoginRequest;
import com.example.ExpenseTracker.model.DTO.SignupRequest;
import com.example.ExpenseTracker.model.User;
import com.example.ExpenseTracker.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request) {

        String email = request.getEmail().trim().toLowerCase();

        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Invalid email or password");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                token
        );
    }

    public void signup(SignupRequest request) {

        String email = request.getEmail().trim().toLowerCase();

        if (userRepo.existsByEmail(email)) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepo.save(user);
    }
}
