package com.example.igrejas.models.profileresponse;

// Comentários adicionados como aluno para explicar melhor o código.

// resposta da API quando se pede o perfil
public class ProfileResponse {
    // campos que guardam os dados deste objeto
    private boolean success;
    private String message;
    private ProfileData data;

    public boolean isSuccess() {
        return success;
    }

    // método para devolver um valor guardado
    public String getMessage() {
        return message;
    }

    public ProfileData getData() {
        return data;
    }
}
