package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.enums.ExpenseCategory;
import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.repo.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepo expenseRepo;

    // ✅ ADD EXPENSE
    public Expense addExpense(Expense expense) {
        return expenseRepo.save(expense);
    }

    // ✅ GET ALL EXPENSES BY USER
    public List<Expense> getExpensesByUser(Long userId) {
        return expenseRepo.findByUser_Id(userId);
    }

    // ✅ SORT LOW → HIGH
    public List<Expense> getExpensesLowToHigh(Long userId) {
        return expenseRepo.findByUser_IdOrderByAmountAsc(userId);
    }

    // ✅ SORT HIGH → LOW
    public List<Expense> getExpensesHighToLow(Long userId) {
        return expenseRepo.findByUser_IdOrderByAmountDesc(userId);
    }

    // ✅ FILTER BY CATEGORY
    public List<Expense> getExpensesByCategory(
            Long userId,
            ExpenseCategory category) {

        return expenseRepo.findByUser_IdAndCategory(userId, category);
    }

    // ✅ FILTER + SORT (LOW → HIGH)
    public List<Expense> getExpensesByCategoryLowToHigh(
            Long userId,
            ExpenseCategory category) {

        return expenseRepo
                .findByUser_IdAndCategoryOrderByAmountAsc(userId, category);
    }

    // ✅ FILTER + SORT (HIGH → LOW)
    public List<Expense> getExpensesByCategoryHighToLow(
            Long userId,
            ExpenseCategory category) {

        return expenseRepo
                .findByUser_IdAndCategoryOrderByAmountDesc(userId, category);
    }

    // ✅ DELETE EXPENSE
    public void deleteExpense(Long id) {
        expenseRepo.deleteById(id);
    }
}
