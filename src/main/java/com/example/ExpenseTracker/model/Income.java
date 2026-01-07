package com.example.ExpenseTracker.model;

@Entity
public class Income {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private IncomeSource source; // INVESTMENT, SALARY, TRADING

    private double amount;
    private LocalDate date;

    @ManyToOne
    private User user;
}

public enum IncomeSource {
    INVESTMENT, SALARY, TRADING
}
