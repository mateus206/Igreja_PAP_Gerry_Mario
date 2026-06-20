package com.example.igrejas.models.defaultresponse;

// Comentários adicionados como aluno para explicar melhor o código.

// resposta simples usada quando só interessa saber sucesso e mensagem
public class SimpleResponse {
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
