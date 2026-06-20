package com.example.igrejas.models;

// Comentários adicionados como aluno para explicar melhor o código.

import com.google.gson.annotations.SerializedName;

// modelo das ações solidárias que são recebidas da API
public class AcaoSolidarias {
    // campos que guardam os dados deste objeto
    private int id;

    @SerializedName("id_user")
    private int idUser;

    @SerializedName("data_hora_inicio")
    private String dataHoraInicio;

    @SerializedName("nome_acao")
    private String nomeAcao;

    // método para devolver um valor guardado
    public int getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getDataHoraInicio() {
        return dataHoraInicio;
    }

    public String getNomeAcao() {
        return nomeAcao;
    }
}
