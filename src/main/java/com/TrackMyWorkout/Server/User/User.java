package com.TrackMyWorkout.Server.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name ="Users")
public class User{

    @Id
    private int id;
    private String userName;
    private String passWord;
    private String email;
    private boolean verified;

    public User(String userName, String password, String email){
        this.userName = userName;
        this.passWord = password;
        this.email = email;
    }

    public User() {

    }
}