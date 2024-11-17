package com.strava.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.AuthRequest;
import com.strava.entity.User;
import com.strava.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register/google")
    public ResponseEntity<User> registerWithGoogle(@Validated @RequestBody AuthRequest request) {
        User user = authService.register(request.getEmail(), "DefaultName", "google");
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Validated @RequestBody AuthRequest request) {
        String token = authService.login(request.getEmail(), request.getPassword(), request.getAuthProvider());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody String token) {
        authService.logout(token);
        return ResponseEntity.noContent().build();
    }
}

