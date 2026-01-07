package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.Income;
import com.example.ExpenseTracker.model.User;
import com.example.ExpenseTracker.repo.IncomeRepo;
import com.example.ExpenseTracker.repo.UserRepo;
import com.example.ExpenseTracker.service.PnlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/income")
public class IncomeController {

    @Autowired
    private IncomeRepo incomeRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PnlService pnlService;

    // ✅ ADD INCOME (FIXED)
    @PostMapping("/add")
    public Income addIncome(
            @RequestBody Income income,
            Authentication authentication
    ) {
        String email = authentication.getName();   // from JWT
        User user = userRepo.findByEmail(email);

        income.setUser(user);                      // 🔥 FIX
        return incomeRepo.save(income);
    }

    // ✅ GET MY INCOME
    @GetMapping("/my")
    public List<Income> getMyIncome(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepo.findByEmail(email);

        return incomeRepo.findByUser_Id(user.getId());
    }

    // ✅ DELETE INCOME
    @DeleteMapping("/{id}")
    public void deleteIncome(@PathVariable Long id) {
        incomeRepo.deleteById(id);
    }

    // ✅ TOTAL INCOME
    @GetMapping("/total")
    public double getTotalIncome(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepo.findByEmail(email);

        return pnlService.getTotalIncome(user.getId());
    }
}
