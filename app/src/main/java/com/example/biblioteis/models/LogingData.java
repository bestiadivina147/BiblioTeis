package com.example.biblioteis.models;

public class LogingData {
    private int id;
    private String usuario;
    private String contrasenha;
    private String mensaje;

    public LogingData(int id, String usuario, String contrasenha, String mensaje) {
        this.id = id;
        this.usuario = usuario;
        this.contrasenha = contrasenha;
        this.mensaje = mensaje;
    }


    public LogingData(int id,String usuario, String contrasenha) {
        this.id=id;
        this.usuario = usuario;
        this.contrasenha = contrasenha;
    }

    public LogingData(String msgError) {
        this.mensaje = msgError;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


}
