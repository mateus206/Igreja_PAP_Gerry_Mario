package com.example.igrejas.models.signupresponse;

// Comentários adicionados como aluno para explicar melhor o código.

// resposta do registo que vem do servidor
public class SignupResponse {
    // campos que guardam os dados deste objeto
    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    // método para devolver um valor guardado
    public String getMessage() {
        return message;
    }
}
