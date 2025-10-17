package com.bhuppi.taskmanager.controller;

import com.bhuppi.taskmanager.model.User;
import com.bhuppi.taskmanager.model.dto.LoginRequest;
import com.bhuppi.taskmanager.model.dto.RegisterRequest;
import com.bhuppi.taskmanager.service.AuthService;
import com.bhuppi.taskmanager.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {
        String token = authService.login(request);
        return Map.of("token", token);
    }
}