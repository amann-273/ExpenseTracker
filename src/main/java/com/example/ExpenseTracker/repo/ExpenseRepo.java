package com.example.ExpenseTracker.repo;

import com.example.ExpenseTracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {

    // 🔹 All expenses
    List<Expense> findAll();

    // 🔹 Sort by amount
    List<Expense> findAllByOrderByAmountAsc();
    List<Expense> findAllByOrderByAmountDesc();

    // 🔹 Filter by category
    List<Expense> findByCategory(String category);
}
