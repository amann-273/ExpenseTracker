package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.service.PnlService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pnl")
@CrossOrigin(origins = "*")
public class PnlController {

    private final PnlService pnlService;

    public PnlController(PnlService pnlService) {
        this.pnlService = pnlService;
    }

    // ✅ TOTAL PnL
    @GetMapping
    public double getTotalPnL() {
        return pnlService.calculateTotalPnL();
    }
}
