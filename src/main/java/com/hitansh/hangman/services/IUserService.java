package com.hitansh.hangman.services;

import com.hitansh.hangman.exceptions.InvalidInputException;
import com.hitansh.hangman.model.User;
import com.hitansh.hangman.requests.LoginUserRequest;
import com.hitansh.hangman.requests.RegisterUserRequest;
import com.hitansh.hangman.requests.ResetPasswordRequest;
import com.hitansh.hangman.requests.UpdateEmailRequest;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    User saveUser(RegisterUserRequest registerUserRequest) throws InvalidInputException;

    String loginUser(LoginUserRequest loginUserRequest) throws InvalidInputException;

    User getUserProfile(String userId);

    User updateEmailOfUser(String userId, UpdateEmailRequest updateEmailRequest) throws InvalidInputException;

    User ResetPasswordOfUser(String userId, ResetPasswordRequest resetPasswordRequest) throws InvalidInputException;

    // void ForgotPasswordSendLinkViaEmail(String email);

}
