package com.example.ExpenseTracker.repo;

import com.example.ExpenseTracker.model.Income;
import com.example.ExpenseTracker.model.User;
import com.example.ExpenseTracker.enums.IncomeSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Long> {

    // 🔹 All income (DO NOT USE in controller anymore)
    List<Income> findAll();

    // 🔹 User-specific income ✅
    List<Income> findByUser(User user);

    // 🔹 Sort by amount (GLOBAL – optional use later)
    List<Income> findAllByOrderByAmountAsc();
    List<Income> findAllByOrderByAmountDesc();

    // 🔹 Filter by source (GLOBAL – optional use later)
    List<Income> findBySource(IncomeSource source);
}
