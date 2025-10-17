package com.bhuppi.taskmanager.model.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}