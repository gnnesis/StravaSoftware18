package com.example.demo;

import com.example.demo.AuthRequest;
import com.example.demo.User;
import com.example.demo.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

