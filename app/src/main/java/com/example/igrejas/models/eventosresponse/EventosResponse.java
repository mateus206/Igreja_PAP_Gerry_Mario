package com.example.igrejas.models.eventosresponse;

// Comentários adicionados como aluno para explicar melhor o código.

// resposta da API para a lista de eventos
public class EventosResponse {
    // campos que guardam os dados deste objeto
    private boolean success;
    private String message;
    private EventosData data;

    public boolean isSuccess() {
        return success;
    }

    // método para devolver um valor guardado
    public String getMessage() {
        return message;
    }

    public EventosData getData() {
        return data;
    }
}
