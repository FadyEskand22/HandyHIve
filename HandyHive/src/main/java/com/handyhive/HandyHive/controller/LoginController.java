package com.handyhive.HandyHive.controller;

import com.handyhive.HandyHive.model.Customer;
import com.handyhive.HandyHive.model.Provider;
import com.handyhive.HandyHive.repository.CustomerRepository;
import com.handyhive.HandyHive.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        // Check if the user is a customer
        Customer customer = customerRepository.findByEmail(request.getEmail()).orElse(null);
        if (customer != null && customer.getPassword().equals(request.getPassword())) {
            return ResponseEntity.ok(new LoginResponse("Login successful!", "customer", "/home"));
        }

        // Check if the user is a provider
        Provider provider = providerRepository.findByEmail(request.getEmail()).orElse(null);
        if (provider != null && provider.getPassword().equals(request.getPassword())) {
            return ResponseEntity.ok(new LoginResponse("Login successful!", "provider", "/provider-dashboard"));
        }

        return ResponseEntity.status(401).body(new LoginResponse("Invalid credentials!", null, null));
    }

    @PostMapping("/register/customer")
public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) {
    try {
        customerRepository.save(customer);
        return ResponseEntity.ok("Registration successful!");
    } catch (Exception e) {
        return ResponseEntity.status(400).body("Registration failed: " + e.getMessage());
    }
}

@PostMapping("/register/provider")
public ResponseEntity<String> registerProvider(@RequestBody Provider provider) {
    try {
        providerRepository.save(provider);
        return ResponseEntity.ok("Registration successful!");
    } catch (Exception e) {
        return ResponseEntity.status(400).body("Registration failed: " + e.getMessage());
    }
}

}

// LoginRequest DTO
class LoginRequest {
    private String email;
    private String password;

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

// LoginResponse DTO
class LoginResponse {
    private String message;
    private String role;
    private String redirectUrl;

    // Constructor
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
