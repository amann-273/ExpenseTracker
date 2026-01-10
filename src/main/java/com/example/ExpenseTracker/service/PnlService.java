package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.model.Income;
import com.example.ExpenseTracker.repo.ExpenseRepo;
import com.example.ExpenseTracker.repo.IncomeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PnlService {

    private final ExpenseRepo expenseRepo;
    private final IncomeRepo incomeRepo;

    // ✅ TOTAL EXPENSE (GLOBAL)
    public double getTotalExpense() {
        return expenseRepo.findAll()
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    // ✅ TOTAL INCOME (GLOBAL)
    public double getTotalIncome() {
        return incomeRepo.findAll()
                .stream()
                .mapToDouble(Income::getAmount)
                .sum();
    }

    // ✅ TOTAL PnL
    public double calculateTotalPnL() {
        return getTotalIncome() - getTotalExpense();
    }
}
