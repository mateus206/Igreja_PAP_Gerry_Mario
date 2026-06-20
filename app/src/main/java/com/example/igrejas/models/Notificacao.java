package com.example.igrejas.models;

import com.google.gson.annotations.SerializedName;

public class Notificacao {
    private int id;
    private String titulo;
    private String mensagem;
    private String tipo;
    private int lida;
    @SerializedName("data_criacao")
    private String dataCriacao;

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getMensagem() { return mensagem; }
    public String getTipo() { return tipo; }
    public int getLida() { return lida; }
    public String getDataCriacao() { return dataCriacao; }
}
