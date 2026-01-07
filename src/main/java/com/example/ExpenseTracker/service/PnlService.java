package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.model.Income;
import com.example.ExpenseTracker.repo.ExpenseRepo;
import com.example.ExpenseTracker.repo.IncomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PnlService {

    @Autowired
    private ExpenseRepo expenseRepo;

    @Autowired
    private IncomeRepo incomeRepo;

    public double getTotalExpense(Long userId) {
        List<Expense> expenses = expenseRepo.findByUser_Id(userId);
        return expenses.stream()
                .mapToDouble(e -> e.getAmount())
                .sum();
    }

    public double getTotalIncome(Long userId) {
        List<Income> incomes = incomeRepo.findByUser_Id(userId);
        return incomes.stream()
                .mapToDouble(i -> i.getAmount())
                .sum();
    }

    public double calculateTotalPnL(Long userId) {
        return getTotalIncome(userId) - getTotalExpense(userId);
    }
}
