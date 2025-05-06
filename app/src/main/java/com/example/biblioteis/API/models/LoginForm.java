package com.example.biblioteis.API.models;

public class LoginForm {
    public String email;
    public String password;

    public LoginForm(String user, String contra) {
        this.email=user;
        this.password = contra;
    }
}
