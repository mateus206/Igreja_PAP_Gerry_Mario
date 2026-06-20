package com.example.igrejas.models.profileresponse;

// Comentários adicionados como aluno para explicar melhor o código.

import com.example.igrejas.models.User;

// parte da resposta do perfil onde vem o utilizador
public class ProfileData {
    // campos que guardam os dados deste objeto
    private User user;

    // método para devolver um valor guardado
    public User getUser() {
        return user;
    }
}
