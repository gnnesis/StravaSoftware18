package com.example.demo;

import com.example.demo.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private final Map<String, User> users = new HashMap<>();

    public User register(String email, String name, String authProvider) {
        if (users.containsKey(email)) {
            throw new IllegalArgumentException("Email already registered");
        }
        User newUser = new User(email, name, authProvider);
        users.put(email, newUser);
        return newUser;
    }

    public String login(String email, String password, String authProvider) {
        User user = users.get(email);
        if (user == null || !user.getAuthProvider().equalsIgnoreCase(authProvider)) {
            throw new IllegalArgumentException("Invalid credentials or provider mismatch");
        }
        return "TokenForUser:" + email; // Simulated token
    }

    public void logout(String token) {
        // Simulate token invalidation (noop in this example)
    }
}

