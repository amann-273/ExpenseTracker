package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.repo.ExpenseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepo expenseRepo;

    public Expense addExpense(Expense expense) {
        return expenseRepo.save(expense);
    }

    public List<Expense> getExpensesByUser(Long userId) {
        return expenseRepo.findByUserId(userId);
    }

    public void deleteExpense(Long id) {
        expenseRepo.deleteById(id);
    }
}
