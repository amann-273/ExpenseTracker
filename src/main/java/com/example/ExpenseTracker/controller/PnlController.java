package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.service.PnlService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pnl")
@CrossOrigin(origins = "*")
public class PnlController {

    private final PnlService pnlService;

    public PnlController(PnlService pnlService) {
        this.pnlService = pnlService;
    }

    @GetMapping
    public double getTotalPnL(Authentication authentication) {
        return pnlService.calculateTotalPnL(authentication.getName());
    }
}
