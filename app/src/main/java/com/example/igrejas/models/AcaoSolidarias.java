package com.example.igrejas.models;

public class AcaoSolidarias {

    private int id;
    private int userId;
    private String dataHoraInicio;
    private String nomeAcao;

    public AcaoSolidarias(int id, int userId, String dataHoraInicio, String nomeAcao) {
        this.id = id;
        this.userId = userId;
        this.dataHoraInicio = dataHoraInicio;
        this.nomeAcao = nomeAcao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(String dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public String getNomeAcao() {
        return nomeAcao;
    }

    public void setNomeAcao(String nomeAcao) {
        this.nomeAcao = nomeAcao;
    }
}
