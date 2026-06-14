package com.example.igrejas.models.eventosresponse;

public class EventosResponse {
    private boolean success;
    private String message;
    private EventosData data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public EventosData getData() {
        return data;
    }
}
