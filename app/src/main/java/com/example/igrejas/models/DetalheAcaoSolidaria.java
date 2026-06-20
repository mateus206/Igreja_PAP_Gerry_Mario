package com.example.igrejas.models;

// Comentários adicionados como aluno para explicar melhor o código.

// modelo com os detalhes de uma ação solidária
public class DetalheAcaoSolidaria {
    // campos que guardam os dados deste objeto
    private  int id;
    private int AcaoSolidariaId;
    private String descricao;
    private String comoAjudar;

    // construtor para criar o objeto já com valores
    public DetalheAcaoSolidaria(int id, int acaoSolidariaId, String descricao, String comoAjudar) {
        this.id = id;
        AcaoSolidariaId = acaoSolidariaId;
        this.descricao = descricao;
        this.comoAjudar = comoAjudar;
    }

    // método para devolver um valor guardado
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
