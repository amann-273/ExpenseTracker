package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.enums.IncomeSource;
import com.example.ExpenseTracker.model.Income;
import com.example.ExpenseTracker.repo.IncomeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeService {

    private final IncomeRepo incomeRepo;

    // ✅ ADD INCOME
    public Income addIncome(Income income) {
        return incomeRepo.save(income);
    }

    // ✅ GET ALL INCOME BY USER
    public List<Income> getIncomeByUser(Long userId) {
        return incomeRepo.findByUser_Id(userId);
    }

    // ✅ SORT LOW → HIGH
    public List<Income> getIncomeLowToHigh(Long userId) {
        return incomeRepo.findByUser_IdOrderByAmountAsc(userId);
    }

    // ✅ SORT HIGH → LOW
    public List<Income> getIncomeHighToLow(Long userId) {
        return incomeRepo.findByUser_IdOrderByAmountDesc(userId);
    }

    // ✅ FILTER BY SOURCE
    public List<Income> getIncomeBySource(
            Long userId,
            IncomeSource source) {

        return incomeRepo.findByUser_IdAndSource(userId, source);
    }

    // ✅ FILTER + SORT (LOW → HIGH)
    public List<Income> getIncomeBySourceLowToHigh(
            Long userId,
            IncomeSource source) {

        return incomeRepo
                .findByUser_IdAndSourceOrderByAmountAsc(userId, source);
    }

    // ✅ FILTER + SORT (HIGH → LOW)
    public List<Income> getIncomeBySourceHighToLow(
            Long userId,
            IncomeSource source) {

        return incomeRepo
                .findByUser_IdAndSourceOrderByAmountDesc(userId, source);
    }

    // ✅ DELETE INCOME
    public void deleteIncome(Long id) {
        incomeRepo.deleteById(id);
    }
}
