package com.example.biblioteis.models;

import android.graphics.Bitmap;

public class LibroDetalle {
    private String image;
    private String titulo;
    private String autor;
    private String fecha;
    private String isbn;
    private Boolean estado;
    private int prestamos;
    private int user;
    private Bitmap img;



    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public int getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(int prestamos) {
        this.prestamos = prestamos;
    }

    public LibroDetalle(String image, String titulo, String autor, String fecha, String isbn, Boolean estado, int prestamos,int user) {
       this(image, titulo, autor, fecha, isbn, estado, prestamos, user, null);
    }

    public LibroDetalle(String image, String titulo, String autor, String fecha, String isbn, Boolean estado, int prestamos,int user, Bitmap img) {
        this.image = image;
        this.titulo = titulo;
        this.autor = autor;
        this.fecha = fecha;
        this.isbn = isbn;
        this.estado = estado;
        this.prestamos = prestamos;
        this.user = user;
        this.img = img;
    }

}
