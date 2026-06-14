package com.example.igrejas.models.profileresponse;

public class ProfileResponse {
    private boolean success;
    private String message;
    private ProfileData data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public ProfileData getData() {
        return data;
    }
}
