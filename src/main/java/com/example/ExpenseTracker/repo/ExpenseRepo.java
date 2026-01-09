package com.example.ExpenseTracker.repo;

import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.enums.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {

    // 🔹 All expenses of user
    List<Expense> findByUser_Id(Long userId);

    // 🔹 Sort by amount
    List<Expense> findByUser_IdOrderByAmountAsc(Long userId);
    List<Expense> findByUser_IdOrderByAmountDesc(Long userId);

    // 🔹 Filter by category
    List<Expense> findByUser_IdAndCategory(Long userId, ExpenseCategory category);

    // 🔹 Filter + Sort (LOW → HIGH)
    List<Expense> findByUser_IdAndCategoryOrderByAmountAsc(
            Long userId,
            ExpenseCategory category
    );

    // 🔹 Filter + Sort (HIGH → LOW)
    List<Expense> findByUser_IdAndCategoryOrderByAmountDesc(
            Long userId,
            ExpenseCategory category
    );

    // ✅ Total Expense (PnL)
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.user.id = :userId")
    double sumExpenseByUser(Long userId);
}
