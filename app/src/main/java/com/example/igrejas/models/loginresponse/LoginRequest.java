package com.example.igrejas.models.loginresponse;

// Comentários adicionados como aluno para explicar melhor o código.

// classe simples para enviar os dados do login
public class LoginRequest {
    // campos que guardam os dados deste objeto
    private String email;
    private String password;

    // construtor para criar o objeto já com valores
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
