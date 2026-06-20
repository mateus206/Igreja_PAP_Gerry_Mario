package com.example.igrejas.models.loginresponse;

// Comentários adicionados como aluno para explicar melhor o código.

// resposta do login que vem do servidor
public class LoginResponse {
    // campos que guardam os dados deste objeto
    private boolean success;
    private String message;
    private LoginData data;
    public boolean isSuccess() {
        return success;
    }
    // método para devolver um valor guardado
    public String getMessage() {
        return message;
    }
    public LoginData getData() {
        return data;
    }
}

