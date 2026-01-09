package com.example.ExpenseTracker.repo;

import com.example.ExpenseTracker.model.Income;
import com.example.ExpenseTracker.enums.IncomeSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Long> {

    // 🔹 All income of user
    List<Income> findByUser_Id(Long userId);

    // 🔹 Sort by amount
    List<Income> findByUser_IdOrderByAmountAsc(Long userId);
    List<Income> findByUser_IdOrderByAmountDesc(Long userId);

    // 🔹 Filter by source
    List<Income> findByUser_IdAndSource(Long userId, IncomeSource source);

    // 🔹 Filter + Sort (LOW → HIGH)
    List<Income> findByUser_IdAndSourceOrderByAmountAsc(
            Long userId,
            IncomeSource source
    );

    // 🔹 Filter + Sort (HIGH → LOW)
    List<Income> findByUser_IdAndSourceOrderByAmountDesc(
            Long userId,
            IncomeSource source
    );

    // ✅ Total Income (PnL)
    @Query("SELECT COALESCE(SUM(i.amount), 0) FROM Income i WHERE i.user.id = :userId")
    double sumIncomeByUser(Long userId);
}
