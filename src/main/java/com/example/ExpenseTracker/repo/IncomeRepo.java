package com.example.ExpenseTracker.repo;

import com.example.ExpenseTracker.model.Income;
import com.example.ExpenseTracker.enums.IncomeSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Long> {

    // 🔹 All income
    List<Income> findAll();

    // 🔹 Sort by amount
    List<Income> findAllByOrderByAmountAsc();
    List<Income> findAllByOrderByAmountDesc();

    // 🔹 Filter by source
    List<Income> findBySource(IncomeSource source);
}
