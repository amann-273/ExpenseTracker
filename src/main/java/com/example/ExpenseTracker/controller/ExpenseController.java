package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.model.User;
import com.example.ExpenseTracker.repo.ExpenseRepo;
import com.example.ExpenseTracker.repo.UserRepo;
import com.example.ExpenseTracker.service.PnlService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expense")
@CrossOrigin(origins = "*")
public class ExpenseController {

    private final ExpenseRepo expenseRepo;
    private final PnlService pnlService;
    private final UserRepo userRepo;

    public ExpenseController(ExpenseRepo expenseRepo, PnlService pnlService, UserRepo userRepo) {
        this.expenseRepo = expenseRepo;
        this.pnlService = pnlService;
        this.userRepo = userRepo;
    }

    // ✅ ADD EXPENSE
    @PostMapping("/add")
    public Expense addExpense(
            @RequestBody Expense expense,
            Authentication authentication
    ) {
        String email = authentication.getName();
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (expense.getCategory() == null) {
            throw new RuntimeException("Expense category is required");
        }

        expense.setId(null);
        expense.setUser(user);
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
