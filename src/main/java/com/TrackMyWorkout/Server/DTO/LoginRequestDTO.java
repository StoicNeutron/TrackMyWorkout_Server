package com.TrackMyWorkout.Server.DTO;

public class LoginRequestDTO{

    private String userName;
    private String passWord;

    public LoginRequestDTO(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }
}