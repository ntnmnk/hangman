package com.hitansh.hangman.requests;

import com.hitansh.hangman.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterUserRequest {

    @NotBlank(message = "Display name is required")
    @Size(min = 5, max = 20, message = "Display name should be between 5 and 20 characters")
    private String displayName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password should be at least 8 characters long")
    private String password;

    private Role role = Role.USER;  // Default role if not provided

    public RegisterUserRequest() {
    }

    public RegisterUserRequest(String displayName, String email, String password, Role role) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    
}
