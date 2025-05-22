package com.example.biblioteis.models;

public class Usuario {
    private String nombre;
    private String correo;
    private String fecha;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Usuario(String nombre, String correo, String fecha) {
        this.nombre = nombre;
        this.correo = correo;
        this.fecha = fecha;
    }

}
