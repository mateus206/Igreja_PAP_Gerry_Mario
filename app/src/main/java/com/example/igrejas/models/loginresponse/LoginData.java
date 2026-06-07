package com.example.igrejas.models.loginresponse;

import com.example.igrejas.models.User;

public class LoginData {
    private User user;
    private String jwt;

    public User getUser() {
        return user;
    }

    public String getJwt() {
        return jwt;
    }
}