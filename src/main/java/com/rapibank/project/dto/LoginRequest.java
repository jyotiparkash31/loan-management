package com.rapibank.project.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @Email
    @NotBlank
    private String emailId;

    @NotBlank
    private String password;

}
