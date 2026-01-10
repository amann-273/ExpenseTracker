package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.enums.IncomeSource;
import com.example.ExpenseTracker.model.Income;
import com.example.ExpenseTracker.model.User;
import com.example.ExpenseTracker.repo.IncomeRepo;
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
public class IncomeService {

    private final IncomeRepo incomeRepo;
    private final UserRepo userRepo;

    // ✅ ADD INCOME
    public Income addIncome(Income income) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepo.findByEmail(userDetails.getUsername());

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (income.getSource() == null) {
            throw new RuntimeException("Income source is required");
        }

        income.setId(null);
        income.setUser(user);
        income.setDate(LocalDate.now());

        return incomeRepo.save(income);
    }

    // ✅ GET ALL INCOME
    public List<Income> getAllIncome() {
        return incomeRepo.findAll();
    }

    // ✅ SORT BY AMOUNT
    public List<Income> getIncomeLowToHigh() {
        return incomeRepo.findAllByOrderByAmountAsc();
    }

    public List<Income> getIncomeHighToLow() {
        return incomeRepo.findAllByOrderByAmountDesc();
    }

    // ✅ FILTER BY SOURCE
    public List<Income> getIncomeBySource(String source) {
        IncomeSource incomeSource;

        try {
            incomeSource = IncomeSource.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid income source: " + source);
        }

        return incomeRepo.findBySource(incomeSource);
    }

    // ✅ DELETE
    public void deleteIncome(Long id) {
        incomeRepo.deleteById(id);
    }
}
