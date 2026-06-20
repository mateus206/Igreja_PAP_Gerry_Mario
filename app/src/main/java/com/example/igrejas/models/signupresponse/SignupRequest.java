package com.example.igrejas.models.signupresponse;

// Comentários adicionados como aluno para explicar melhor o código.

// classe simples para enviar os dados do registo
public class SignupRequest {
    // campos que guardam os dados deste objeto
    private String nome;
    private String email;
    private String telefone;
    private String password;

    // construtor para criar o objeto já com valores
    public SignupRequest(String nome, String email, String telefone, String password){
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.password = password;
    }
}
