package com.TrackMyWorkout.Server.Controllers;

import com.TrackMyWorkout.Server.DTO.LoginRequestDTO;
import com.TrackMyWorkout.Server.Entities.User;
import com.TrackMyWorkout.Server.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController{

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping ("/new_user")
    public ResponseEntity<User> signup(){
        User user = this.userService.saveNewUser("sampleUser", "samplePassword", "user@email.com");
        System.out.println("Called for new user");
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/auth")
    public ResponseEntity<String> login(){
        String userName = "sampleUser";
        String password = "samplePassword";
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO(userName, password);
        if(this.userService.authenticateUser(loginRequestDTO)){
            System.out.println("Called and authenticated");
            return new ResponseEntity<>(null, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}