package com.example.igrejas.models.acaosolidariasresponse;

// Comentários adicionados como aluno para explicar melhor o código.

// resposta da API para a lista de ações solidárias
public class AcoesSolidariasResponse {
    // campos que guardam os dados deste objeto
    private boolean success;
    private String message;
    private AcoesSolidariasData data;

    public boolean isSuccess() {
        return success;
    }

    // método para devolver um valor guardado
    public String getMessage() {
        return message;
    }

    public AcoesSolidariasData getData() {
        return data;
    }
}
