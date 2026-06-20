package com.example.igrejas.models.loginresponse;

// Comentários adicionados como aluno para explicar melhor o código.

import com.example.igrejas.models.User;

// parte da resposta do login que traz o utilizador e o token
public class LoginData {
    // campos que guardam os dados deste objeto
    private User user;
    private String jwt;

    // método para devolver um valor guardado
    public User getUser() {
        return user;
    }

    public String getJwt() {
        return jwt;
    }
}
