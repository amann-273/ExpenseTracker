package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.repo.ExpenseRepo;
import com.example.ExpenseTracker.service.PnlService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expense")
@CrossOrigin(origins = "*")
public class ExpenseController {

    private final ExpenseRepo expenseRepo;
    private final PnlService pnlService;

    public ExpenseController(ExpenseRepo expenseRepo, PnlService pnlService) {
        this.expenseRepo = expenseRepo;
        this.pnlService = pnlService;
    }

    // ✅ ADD EXPENSE
    @PostMapping("/add")
    public Expense addExpense(@RequestBody Expense expense) {
        expense.setId(null);
        expense.setDate(LocalDate.now());
        return expenseRepo.save(expense);
    }

    // ✅ GET ALL EXPENSES
    @GetMapping("/all")
    public List<Expense> getAllExpenses() {
        return expenseRepo.findAll();
    }

    // ✅ DELETE EXPENSE
    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseRepo.deleteById(id);
    }

    // ✅ TOTAL EXPENSE
    @GetMapping("/total")
    public double getTotalExpense() {
        return pnlService.getTotalExpense();
    }
}
