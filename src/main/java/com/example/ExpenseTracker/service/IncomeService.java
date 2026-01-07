package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.Income;
import com.example.ExpenseTracker.repo.IncomeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeService {

    private final IncomeRepo incomeRepo;

    public Income addIncome(Income income) {
        return incomeRepo.save(income);
    }

    public List<Income> getIncomeByUser(Long userId) {
        return incomeRepo.findByUserId(userId);
    }

    public void deleteIncome(Long id) {
        incomeRepo.deleteById(id);
    }
}
