package com.hitansh.hangman.requests;

public class ResetPasswordRequest {

    private String oldPassword;
    private String newPassword;

    public ResetPasswordRequest(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }


    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }



    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    
    
}
