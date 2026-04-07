package com.example.igrejas.models;

public class PedidoOracoes {
    private int id;
    private int userId;
    private String email;
    private String tipoPedido;
    private String descricao;

    public PedidoOracoes(int id, int userId, String email, String tipoPedido, String descricao) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.tipoPedido = tipoPedido;
        this.descricao = descricao;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
