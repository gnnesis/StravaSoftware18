package com.strava.entity;

public class User {
    private String email;
    private String name;
    private String authProvider;

    public User(String email, String name, String authProvider) {
        this.email = email;
        this.name = name;
        this.authProvider = authProvider;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAuthProvider() {
        return authProvider;
    }
}

