package com.example.igrejas.models.eventosresponse;

// Comentários adicionados como aluno para explicar melhor o código.

import com.example.igrejas.models.Eventos;
import java.util.ArrayList;

// aqui fica a lista de eventos dentro da resposta
public class EventosData {
    // campos que guardam os dados deste objeto
    private ArrayList<Eventos> eventos;

    // método para devolver um valor guardado
    public ArrayList<Eventos> getEventos() {
        return eventos;
    }
}
