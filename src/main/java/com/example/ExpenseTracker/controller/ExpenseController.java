package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.service.ExpenseService;
import com.example.ExpenseTracker.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;
    private final JwtService jwtService;

    @GetMapping
    public List<Expense> getExpenses(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        String email = jwtService.extractUsername(token);
        return expenseService.getExpenses(email);
    }

    @PostMapping
    public Expense addExpense(@RequestHeader("Authorization") String authHeader, @RequestBody Expense expense) {
        String token = authHeader.substring(7);
        String email = jwtService.extractUsername(token);
        return expenseService.addExpense(expense, email);
    }
}
