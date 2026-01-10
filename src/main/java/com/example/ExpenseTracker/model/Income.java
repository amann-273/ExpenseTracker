package com.example.ExpenseTracker.model;

import com.example.ExpenseTracker.enums.IncomeSource;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "income")
@Getter
@Setter
@NoArgsConstructor
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IncomeSource source;

    private double amount;

    private LocalDate date;
}
