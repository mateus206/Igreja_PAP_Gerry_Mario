package com.example.igrejas.models.acaosolidariasresponse;

// Comentários adicionados como aluno para explicar melhor o código.

import com.example.igrejas.models.AcaoSolidarias;
import java.util.ArrayList;

// aqui fica a lista de ações solidárias dentro da resposta
public class AcoesSolidariasData {
    // campos que guardam os dados deste objeto
    private ArrayList<AcaoSolidarias> acoes_solidarias;

    // método para devolver um valor guardado
    public ArrayList<AcaoSolidarias> getAcoesSolidarias() {
        return acoes_solidarias;
    }
}
