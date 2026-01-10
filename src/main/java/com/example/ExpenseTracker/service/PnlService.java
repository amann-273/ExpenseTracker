package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.repo.ExpenseRepo;
import com.example.ExpenseTracker.repo.IncomeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PnlService {

    private final ExpenseRepo expenseRepo;
    private final IncomeRepo incomeRepo;

    public double getTotalExpense(Long userId) {
        return expenseRepo.sumExpenseByUser(userId);
    }

    public double getTotalIncome(Long userId) {
        return incomeRepo.sumIncomeByUser(userId);
    }

    public double calculateTotalPnL(Long userId) {
        return getTotalIncome(userId) - getTotalExpense(userId);
    }
}
