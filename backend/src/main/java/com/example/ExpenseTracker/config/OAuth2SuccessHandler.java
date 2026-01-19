package com.example.ExpenseTracker.config;

import com.example.ExpenseTracker.model.User;
import com.example.ExpenseTracker.repo.UserRepo;
import com.example.ExpenseTracker.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepo userRepo;
    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        User user = userRepo.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setPassword(""); // OAuth user
            userRepo.save(user);
        }

        // ✅ GENERATE JWT
        String token = jwtService.generateToken(user.getEmail());

        // ✅ REDIRECT TO FRONTEND WITH TOKEN
        response.sendRedirect(
                "http://localhost:3000/oauth-success.html?token=" + token
        );
    }
}
