package com.example.biblioteis.models;

import java.util.List;

public class Usuario {

    private int id;
    private String nombre;
    private String correo;
    private String fecha;
    private List<LibroLending> libros;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public List<LibroLending> getLibros() {
        return libros;
    }

    public void setLibros(List<LibroLending> libros) {
        this.libros = libros;
    }

    public Usuario(int id, String nombre, String correo, String fecha, List<LibroLending> libros) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.fecha = fecha;
        this.libros = libros;
    }
}
