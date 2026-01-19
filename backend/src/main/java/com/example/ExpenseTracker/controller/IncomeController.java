package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.Income;
import com.example.ExpenseTracker.model.User;
import com.example.ExpenseTracker.repo.IncomeRepo;
import com.example.ExpenseTracker.repo.UserRepo;
import com.example.ExpenseTracker.service.PnlService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/income")
@CrossOrigin(origins = "*")
public class IncomeController {

    private final IncomeRepo incomeRepo;
    private final PnlService pnlService;
    private final UserRepo userRepo;

    public IncomeController(IncomeRepo incomeRepo, PnlService pnlService, UserRepo userRepo) {
        this.incomeRepo = incomeRepo;
        this.pnlService = pnlService;
        this.userRepo = userRepo;
    }

    @PostMapping("/add")
    public Income addIncome(@RequestBody Income income, Authentication authentication) {

        User user = userRepo.findByEmail(authentication.getName());
        if (user == null) throw new RuntimeException("User not found");
        if (income.getSource() == null) throw new RuntimeException("Income source is required");

        income.setId(null);
        income.setUser(user);
        income.setDate(LocalDate.now());

        return incomeRepo.save(income);
    }

    @GetMapping("/all")
    public List<Income> getAllIncome(Authentication authentication) {

        User user = userRepo.findByEmail(authentication.getName());
        if (user == null) throw new RuntimeException("User not found");

        return incomeRepo.findByUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteIncome(@PathVariable Long id, Authentication authentication) {

        User user = userRepo.findByEmail(authentication.getName());

        Income income = incomeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Income not found"));

        if (!income.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        incomeRepo.delete(income);
    }

    // ✅ USER-SPECIFIC TOTAL
    @GetMapping("/total")
    public double getTotalIncome(Authentication authentication) {
        return pnlService.getTotalIncome(authentication.getName());
    }
}
