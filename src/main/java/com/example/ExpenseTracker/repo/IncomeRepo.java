package com.example.ExpenseTracker.repo;

import com.example.ExpenseTracker.model.Income;
import com.example.ExpenseTracker.enums.IncomeSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Long> {
    List<Income> findByUserId(Long userId);
    List<Income> findByUserIdOrderByAmountAsc(Long userId);
    List<Income> findByUserIdOrderByAmountDesc(Long userId);
    List<Income> findByUserIdAndSource(Long userId, IncomeSource source);
}
