package com.example.ExpenseTracker.model.DTO;

import lombok.Data;

@Data
public class SignupRequest {

    private String name;
    private String email;
    private String password;
}
