package com.example.igrejas.models;

import com.google.gson.annotations.SerializedName;

public class AcaoSolidarias {
    private int id;

    @SerializedName("id_user")
    private int idUser;

    @SerializedName("data_hora_inicio")
    private String dataHoraInicio;

    @SerializedName("nome_acao")
    private String nomeAcao;

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
