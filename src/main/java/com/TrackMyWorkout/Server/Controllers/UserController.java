package com.TrackMyWorkout.Server.Controllers;

import com.TrackMyWorkout.Server.DTO.LoginRequestDTO;
import com.TrackMyWorkout.Server.DTO.SignUpRequestDTO;
import com.TrackMyWorkout.Server.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Samnang Thorn
 */
@RestController
public class UserController{

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping (value = "/new_user", consumes = "application/json")
    public ResponseEntity<String> signup(@RequestBody SignUpRequestDTO signUpRequestDTO){
        // safe check
        if(signUpRequestDTO == null){
            return new ResponseEntity<>("User Information Not Given!", HttpStatus.BAD_REQUEST);
        }
        if(signUpRequestDTO.getUserName() == null || signUpRequestDTO.getPassword() == null || signUpRequestDTO.getEmail() == null){
            return new ResponseEntity<>("Incomplete User Information!", HttpStatus.BAD_REQUEST);
        }
        // end of safe check
        this.userService.saveNewUser(signUpRequestDTO.getUserName(), signUpRequestDTO.getPassword(), signUpRequestDTO.getEmail());
        return new ResponseEntity<>("Success: User Created!", HttpStatus.CREATED);
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO){
        // safe check
        if(loginRequestDTO == null){
            return new ResponseEntity<>("Login Information Not Given!", HttpStatus.BAD_REQUEST);
        }
        if(loginRequestDTO.getUserName() == null || loginRequestDTO.getPassWord() == null){
            return new ResponseEntity<>("Incomplete Login Information!", HttpStatus.BAD_REQUEST);
        }
        // end of safe check
        if(this.userService.authenticateUser(loginRequestDTO)){
            return new ResponseEntity<>("Success: Logging in!", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Incorrect Password!", HttpStatus.BAD_REQUEST);
        }
    }
}