package com.example.igrejas.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Eventos {
    private String titulo;

    private String tipo;
    private String local;
    private String dataHora;



    public  Eventos (String titulo, String tipo, String local, String dataHora){
        this.titulo = titulo;
        this.dataHora = dataHora;
        this.tipo=tipo;
        this.local=local;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return "Eventos{" +
                "titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", local='" + local + '\'' +
                ", dataHora='" + dataHora + '\'' +
                '}';
    }
}

