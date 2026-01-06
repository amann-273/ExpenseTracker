package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.DTO.LoginRequest;
import com.example.ExpenseTracker.model.DTO.SignupRequest;
import com.example.ExpenseTracker.model.User;
import com.example.ExpenseTracker.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // LOGIN
    public String login(LoginRequest request) {

        User user = userRepo.findByEmail(request.getEmail());

        if (user == null) {
            throw new RuntimeException("Invalid email or password");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return "Login successful";
    }

    // SIGNUP
    public String signup(SignupRequest request) {

        if (userRepo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepo.save(user);

        return "Signup successful";
    }
}
