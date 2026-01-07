package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.enums.ExpenseCategory;
import com.example.ExpenseTracker.enums.IncomeSource;
import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.model.Income;
import com.example.ExpenseTracker.service.ExpenseService;
import com.example.ExpenseTracker.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;
    private final IncomeService incomeService;

    @PostMapping("/expense")
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseService.addExpense(expense);
    }

    @PostMapping("/income")
    public Income addIncome(@RequestBody Income income) {
        return incomeService.addIncome(income);
    }

    @GetMapping("/expenses/{userId}")
    public List<Expense> getExpenses(@PathVariable Long userId) {
        return expenseService.getExpensesByUser(userId);
    }

    @GetMapping("/income/{userId}")
    public List<Income> getIncome(@PathVariable Long userId) {
        return incomeService.getIncomeByUser(userId);
    }

    @GetMapping("/totalPnL/{userId}")
    public double getTotalPnL(@PathVariable Long userId) {
        double income = incomeService.getIncomeByUser(userId)
                .stream().mapToDouble(Income::getAmount).sum();

        double expense = expenseService.getExpensesByUser(userId)
                .stream().mapToDouble(Expense::getAmount).sum();

        return income - expense;
    }
}
