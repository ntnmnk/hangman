package com.hitansh.hangman.responses;

import com.hitansh.hangman.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordResponse {

    private User user;

    public ResetPasswordResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
