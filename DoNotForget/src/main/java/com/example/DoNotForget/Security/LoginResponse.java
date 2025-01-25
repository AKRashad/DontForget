package com.example.DoNotForget.Security;  // Use your actual package name

public class LoginResponse {
    private String token;  // This will hold the JWT token

    // Constructor
    public LoginResponse(String token) {
        this.token = token;
    }

    // Getter method for token
    public String getToken() {
        return token;
    }

    // Setter method for token (optional, but useful for serialization/deserialization)
    public void setToken(String token) {
        this.token = token;
    }
}
