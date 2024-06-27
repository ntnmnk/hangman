package com.hitansh.hangman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hitansh.hangman.services.IUserService;

//In this file we are writing test cases for UserService.java using JUnit and Mockito
@SpringBootTest
public class UserServiceTest{

    @Autowired
    private IUserService userService;

    
}