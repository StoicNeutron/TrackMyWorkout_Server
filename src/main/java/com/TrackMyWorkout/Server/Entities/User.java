package com.TrackMyWorkout.Server.Entities;

import jakarta.persistence.*;

/**
 * @author Samnang Thorn
 */
@Entity(name ="users")
public class User{

    @SequenceGenerator(name = "User_sequence", sequenceName = "User_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_sequence")
    @Id
    @Column(name = "userId")
    private Integer userId;
    @Column(name = "userName", nullable = false, columnDefinition = "TEXT")
    private String userName;
    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String passWord;
    @Column(name = "salt", nullable = false, columnDefinition = "TEXT")
    private String salt;
    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;
    @Column(name = "verified", nullable = false, columnDefinition = "BOOLEAN")
    private boolean verified;
    @Column(name = "role", nullable = false, columnDefinition = "TEXT")
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
        this.userName = null;
        this.passWord = null;
        this.salt = null;
        this.email = null;
        this.verified = false;
    }

    public User(String userName, String password, String salt, String email, Role role){
        this.userName = userName;
        this.passWord = password;
        this.salt = salt;
        this.email = email;
        this.role = Role.GUEST;
    }

    public String getUserName() {
        return userName;
    }
    public String getSalt() {
        return salt;
    }

    public String getPassword() {
        return passWord;
    }

    public String getEmail() {
        return email;
    }
}
