package com.example.sportrates.model;

import lombok.Data;

@Data
public class RegisterUserCredentials {
    private String login;
    private String password;
    private String userName;
    private Integer initialBalanceSum;
}
