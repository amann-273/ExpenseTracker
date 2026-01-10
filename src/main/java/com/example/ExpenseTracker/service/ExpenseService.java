package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.model.User;
import com.example.ExpenseTracker.repo.ExpenseRepo;
import com.example.ExpenseTracker.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepo expenseRepo;
    private final UserRepo userRepo;

    // ✅ ADD EXPENSE
    public Expense addExpense(Expense expense) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepo.findByEmail(userDetails.getUsername());

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        expense.setId(null);
        expense.setUser(user);
        expense.setDate(LocalDate.now());

        return expenseRepo.save(expense);
    }

    // ✅ GET ALL EXPENSES
    public List<Expense> getAllExpenses() {
        return expenseRepo.findAll();
    }

    // ✅ SORT BY AMOUNT
    public List<Expense> getExpensesLowToHigh() {
        return expenseRepo.findAllByOrderByAmountAsc();
    }

    public List<Expense> getExpensesHighToLow() {
        return expenseRepo.findAllByOrderByAmountDesc();
    }

    // ✅ FILTER BY CATEGORY
    public List<Expense> getExpensesByCategory(String category) {
        return expenseRepo.findByCategory(category);
    }

    // ✅ DELETE
    public void deleteExpense(Long id) {
        expenseRepo.deleteById(id);
    }
}
