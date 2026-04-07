package com.example.igrejas.models;

public class DetalheAcaoSolidaria {
    private  int id;
    private int AcaoSolidariaId;
    private String descricao;
    private String comoAjudar;

    public DetalheAcaoSolidaria(int id, int acaoSolidariaId, String descricao, String comoAjudar) {
        this.id = id;
        AcaoSolidariaId = acaoSolidariaId;
        this.descricao = descricao;
        this.comoAjudar = comoAjudar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAcaoSolidariaId() {
        return AcaoSolidariaId;
    }

    public void setAcaoSolidariaId(int acaoSolidariaId) {
        AcaoSolidariaId = acaoSolidariaId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getComoAjudar() {
        return comoAjudar;
    }

    public void setComoAjudar(String comoAjudar) {
        this.comoAjudar = comoAjudar;
    }
}
