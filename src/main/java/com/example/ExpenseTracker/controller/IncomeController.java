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

    // ✅ ADD INCOME
    @PostMapping("/add")
    public Income addIncome(
            @RequestBody Income income,
            Authentication authentication
    ) {
        String email = authentication.getName();
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (income.getSource() == null) {
            throw new RuntimeException("Income source is required");
        }

        income.setId(null);
        income.setUser(user);
        income.setDate(LocalDate.now());

        return incomeRepo.save(income);
    }


    // ✅ GET ALL INCOME
    @GetMapping("/all")
    public List<Income> getAllIncome() {
        return incomeRepo.findAll();
    }

    // ✅ DELETE INCOME
    @DeleteMapping("/{id}")
    public void deleteIncome(@PathVariable Long id) {
        incomeRepo.deleteById(id);
    }

    // ✅ TOTAL INCOME
    @GetMapping("/total")
    public double getTotalIncome() {
        return pnlService.getTotalIncome();
    }
}
