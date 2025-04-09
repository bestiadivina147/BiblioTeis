package com.example.biblioteis.models;

import java.sql.Date;

public class Libro {
    private String titulo;
    private String autor;
    private Date fechaPublicacion;
    private String isbn;
    private String usuario;
    private String imagen;
    private int disponibles;
    private int totales;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    public int getTotales() {
        return totales;
    }

    public void setTotales(int totales) {
        this.totales = totales;
    }

    public Libro(String titulo, String autor, Date fechaPublicacion, String isbn, String usuario, String imagen, int disponibles, int totales) {
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
        this.isbn = isbn;
        this.usuario = usuario;
        this.imagen = imagen;
        this.disponibles = disponibles;
        this.totales = totales;
    }

    public Libro() {
    }
}
