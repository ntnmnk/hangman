package com.hitansh.hangman.responses;

import com.hitansh.hangman.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserProfileResponse {

    private String email;
    private String displayName;
    private Role role;

    public GetUserProfileResponse(String email, String displayName, Role role) {
        this.email = email;
        this.displayName = displayName;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Role getRole() {
        return role;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
}
