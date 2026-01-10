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

    /* ================= ADD ================= */

    public Income addIncome(Income income) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

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

    /* ================= READ ================= */

    public List<Income> getMyIncome() {
        User user = getLoggedInUser();
        return incomeRepo.findByUser_Id(user.getId());
    }

    public List<Income> getMyIncomeLowToHigh() {
        User user = getLoggedInUser();
        return incomeRepo.findByUser_IdOrderByAmountAsc(user.getId());
    }

    public List<Income> getMyIncomeHighToLow() {
        User user = getLoggedInUser();
        return incomeRepo.findByUser_IdOrderByAmountDesc(user.getId());
    }

    public List<Income> getMyIncomeBySource(String source) {
        User user = getLoggedInUser();

        IncomeSource incomeSource;
        try {
            incomeSource = IncomeSource.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid income source: " + source);
        }

        return incomeRepo.findByUser_IdAndSource(user.getId(), incomeSource);
    }

    /* ================= DELETE ================= */

    public void deleteIncome(Long id) {
        incomeRepo.deleteById(id);
    }

    /* ================= UTIL ================= */

    private User getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        User user = userRepo.findByEmail(userDetails.getUsername());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }
}
