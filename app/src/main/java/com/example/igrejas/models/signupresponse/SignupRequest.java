package com.example.igrejas.models.signupresponse;

public class SignupRequest {
    private String nome;
    private String email;
    private String telefone;
    private String password;

    public SignupRequest(String nome, String email, String telefone, String password){
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.password = password;
    }
}
