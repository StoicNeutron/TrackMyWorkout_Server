package com.TrackMyWorkout.Server.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController{

    @GetMapping("/new_user")
    public User createUser(){
        return new User("sampleUser", "123", "user@email.com");
    }
}