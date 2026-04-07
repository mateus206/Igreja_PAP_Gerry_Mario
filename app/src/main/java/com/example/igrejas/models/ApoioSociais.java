package com.example.igrejas.models;

public class ApoioSociais {

    private int id;
    private int userId;
    private String local;
    private String codigoPostal;
    private String telefone;
    private int membrosDeFamilia;
    private String pedidoAjuda;

    public ApoioSociais(int id, int userId, String local, String codigoPostal, String telefone, int membrosDeFamilia, String pedidoAjuda) {
        this.id = id;
        this.userId = userId;
        this.local = local;
        this.codigoPostal = codigoPostal;
        this.telefone = telefone;
        this.membrosDeFamilia = membrosDeFamilia;
        this.pedidoAjuda = pedidoAjuda;
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getMembrosDeFamilia() {
        return membrosDeFamilia;
    }

    public void setMembrosDeFamilia(int membrosDeFamilia) {
        this.membrosDeFamilia = membrosDeFamilia;
    }

    public String getPedidoAjuda() {
        return pedidoAjuda;
    }

    public void setPedidoAjuda(String pedidoAjuda) {
        this.pedidoAjuda = pedidoAjuda;
    }

    @Override
    public String toString() {
        return "ApoioSociais{" +
                "id=" + id +
                ", userId=" + userId +
                ", local='" + local + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", telefone='" + telefone + '\'' +
                ", membrosDeFamilia=" + membrosDeFamilia +
                ", pedidoAjuda='" + pedidoAjuda + '\'' +
                '}';
    }
}
