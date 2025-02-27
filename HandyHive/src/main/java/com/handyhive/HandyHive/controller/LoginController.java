package com.handyhive.HandyHive.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    // In a real application, inject your CustomerRepository and ProviderRepository here
    // and check the database for matching credentials.

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        // For demonstration, we use hardcoded values.
        if (request.getEmail().equals("customer@example.com") &&
            request.getPassword().equals("customer123")) {
            // Customer credentials match
            return ResponseEntity.ok(new LoginResponse("Login successful!", "customer", "/customer-home.html"));
        } else if (request.getEmail().equals("provider@example.com") &&
                   request.getPassword().equals("provider123")) {
            // Provider credentials match
            return ResponseEntity.ok(new LoginResponse("Login successful!", "provider", "/provider-dashboard.html"));
        }
        // If no match, return an unauthorized response.
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new LoginResponse("Invalid credentials!", null, null));
    }
}

// DTO classes for login
class LoginRequest {
    private String email;
    private String password;

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

class LoginResponse {
    private String message;
    private String role;
    private String redirectUrl;

    public LoginResponse(String message, String role, String redirectUrl) {
        this.message = message;
        this.role = role;
        this.redirectUrl = redirectUrl;
    }

    // Getters
    public String getMessage() { return message; }
    public String getRole() { return role; }
    public String getRedirectUrl() { return redirectUrl; }
}
