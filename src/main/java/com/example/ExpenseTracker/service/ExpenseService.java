package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.model.User;
import com.example.ExpenseTracker.repo.ExpenseRepo;
import com.example.ExpenseTracker.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepo expenseRepo;
    private final UserRepo userRepo;

    public List<Expense> getExpenses(String userEmail) {
        User user = userRepo.findByEmail(userEmail);
        return expenseRepo.findByUser(user);
    }

    public Expense addExpense(Expense expense, String userEmail) {
        User user = userRepo.findByEmail(userEmail);
        expense.setUser(user);
        return expenseRepo.save(expense);
    }
}
