package com.TrackMyWorkout.Server.DTO;

/**
 * @author Samnang Thorn
 */
public class LoginRequestDTO{

    private String email;
    private String passWord;

    public LoginRequestDTO(String email, String passWord) {
        this.email = email;
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public String getPassWord() {
        return passWord;
    }
}