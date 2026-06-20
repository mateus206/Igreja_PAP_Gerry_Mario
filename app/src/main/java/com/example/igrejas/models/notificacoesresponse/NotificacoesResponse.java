package com.example.igrejas.models.notificacoesresponse;

public class NotificacoesResponse {
    private boolean success;
    private String message;
    private NotificacoesData data;

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public NotificacoesData getData() { return data; }
}
