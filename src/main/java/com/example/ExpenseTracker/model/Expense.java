package com.example.ExpenseTracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Expense {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private ExpenseCategory category; // PERSONAL, SURVIVAL, INVESTMENT

    private double amount;
    private LocalDate date;

    @ManyToOne
    private User user; // owner of this expense
}

public enum ExpenseCategory {
    PERSONAL, SURVIVAL, INVESTMENT
}


