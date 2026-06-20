package com.example.igrejas.models;

// Comentários adicionados como aluno para explicar melhor o código.

import com.google.gson.annotations.SerializedName;

// modelo dos eventos, com os nomes iguais aos campos da API quando é preciso
public class Eventos {
    // campos que guardam os dados deste objeto
    private int id;

    @SerializedName("id_users")
    private int idUsers;

    @SerializedName("data_hora_inicio")
    private String dataHoraInicio;

    @SerializedName("nome_evento")
    private String nomeEvento;

    @SerializedName("tipo_evento")
    private String tipoEvento;

    private String local;

    // construtor para criar o objeto já com valores
    public Eventos(String titulo, String tipo, String local, String dataHora) {
        this.nomeEvento = titulo;
        this.tipoEvento = tipo;
        this.local = local;
        this.dataHoraInicio = dataHora;
    }

    // método para devolver um valor guardado
    public int getId() {
        return id;
    }

    public int getIdUsers() {
        return idUsers;
    }

    public String getDataHoraInicio() {
        return dataHoraInicio;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public String getLocal() {
        return local;
    }

    public String getTitulo() {
        return nomeEvento;
    }

    public String getTipo() {
        return tipoEvento;
    }

    public String getDataHora() {
        return dataHoraInicio;
    }
}
