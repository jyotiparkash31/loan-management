package com.rapibank.project.dto;


import lombok.Data;

@Data
public class RegisterRequest {
    private String emailId;
    private String password;
    private String role;
}