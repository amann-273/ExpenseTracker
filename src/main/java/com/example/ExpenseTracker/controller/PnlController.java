package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.User;
import com.example.ExpenseTracker.repo.UserRepo;
import com.example.ExpenseTracker.service.PnlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pnl")
public class PnlController {

    @Autowired
    private PnlService pnlService;

    @Autowired
    private UserRepo userRepo;

    // ✅ JWT BASED PnL (NO userId IN URL)
    @GetMapping
    public double getTotalPnL(Authentication authentication)
    {

        String email = authentication.getName(); // comes from JWT
        User user = userRepo.findByEmail(email);

        return pnlService.calculateTotalPnL(user.getId());
    }
}
