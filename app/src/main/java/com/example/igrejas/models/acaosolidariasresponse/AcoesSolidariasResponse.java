package com.example.igrejas.models.acaosolidariasresponse;

public class AcoesSolidariasResponse {
    private boolean success;
    private String message;
    private AcoesSolidariasData data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public AcoesSolidariasData getData() {
        return data;
    }
}
