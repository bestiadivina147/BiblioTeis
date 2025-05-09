package com.example.biblioteis.models;

public class LibroDetalle {
    private String image;
    private String titulo;
    private String autor;
    private String fecha;
    private String isbn;
    private String estado;
    private int prestamos;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(int prestamos) {
        this.prestamos = prestamos;
    }

    public LibroDetalle(String image, String titulo, String autor, String fecha, String isbn, String estado, int prestamos) {
        this.image = image;
        this.titulo = titulo;
        this.autor = autor;
        this.fecha = fecha;
        this.isbn = isbn;
        this.estado = estado;
        this.prestamos = prestamos;
    }
}
