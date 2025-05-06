package com.example.biblioteis.models;

public class LogingData {
    private String usuario;
    private String contrasenha;
    private String mensaje;

    public LogingData(String usuario, String contrasenha, String mensaje) {
        this.usuario = usuario;
        this.contrasenha = contrasenha;
        this.mensaje = mensaje;
    }

    public LogingData(String usuario, String contrasenha) {
        this.usuario = usuario;
        this.contrasenha = contrasenha;
    }

    public LogingData(String msgError) {
        this.mensaje = msgError;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
