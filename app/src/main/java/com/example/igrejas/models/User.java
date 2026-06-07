package com.example.igrejas.models;

import com.google.gson.annotations.SerializedName;
public class User {

    private int id;
    @SerializedName("is_admin")
    private boolean isAdmin;
    private String nome;
    private String telefone;
    private String email;
    @SerializedName("data_registro")
    private String dataRegistro;
    private String estado;
    private String password;
    @SerializedName("is_verified")
    private boolean isVerified;


    public User(int id, boolean isAdmin, String nome, String telefone,
                String email, String dataRegistro, String estado,
                String password, boolean isVerified) {
        this.id = id;
        this.isAdmin = isAdmin;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataRegistro = dataRegistro;
        this.estado = estado;
        this.password = password;
        this.isVerified = isVerified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", isAdmin=" + isAdmin +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", dataRegistro='" + dataRegistro + '\'' +
                ", estado='" + estado + '\'' +
                ", password='" + password + '\'' +
                ", isVerified=" + isVerified +
                '}';
    }
}
