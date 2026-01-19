package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.model.Income;
import com.example.ExpenseTracker.model.User;
import com.example.ExpenseTracker.repo.ExpenseRepo;
import com.example.ExpenseTracker.repo.IncomeRepo;
import com.example.ExpenseTracker.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PnlService {

    private final ExpenseRepo expenseRepo;
    private final IncomeRepo incomeRepo;
    private final UserRepo userRepo;

    // ✅ USER-SPECIFIC TOTAL EXPENSE
    public double getTotalExpense(String email) {

        User user = userRepo.findByEmail(email);
        if (user == null) throw new RuntimeException("User not found");

        return expenseRepo.findByUser(user)
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    // ✅ USER-SPECIFIC TOTAL INCOME
    public double getTotalIncome(String email) {

        User user = userRepo.findByEmail(email);
        if (user == null) throw new RuntimeException("User not found");

        return incomeRepo.findByUser(user)
                .stream()
                .mapToDouble(Income::getAmount)
                .sum();
    }

    // ✅ USER-SPECIFIC PnL
    public double calculateTotalPnL(String email) {
        return getTotalIncome(email) - getTotalExpense(email);
    }
}
