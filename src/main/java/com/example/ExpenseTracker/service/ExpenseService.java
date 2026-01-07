package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.repo.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepo expenseRepo;   // ✅ THIS WAS MISSING

    // ✅ ADD EXPENSE
    public Expense addExpense(Expense expense) {
        return expenseRepo.save(expense);
    }

    // ✅ GET ALL EXPENSES BY USER
    public List<Expense> getExpensesByUser(Long userId) {
        return expenseRepo.findByUser_Id(userId);
    }

    // ✅ DELETE EXPENSE
    public void deleteExpense(Long id) {
        expenseRepo.deleteById(id);
    }
}
