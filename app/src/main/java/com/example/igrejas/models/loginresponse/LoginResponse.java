package com.example.igrejas.models.loginresponse;

public class LoginResponse {
    private boolean success;
    private String message;
    private LoginData data;
    public boolean isSuccess() {
        return success;
    }
    public String getMessage() {
        return message;
    }
    public LoginData getData() {
        return data;
    }
}

