package com.example.biblioteis.models;

import android.graphics.Bitmap;


public class Libro {
    private Integer id;
    private String titulo;
    private String autor;
    private String fechaPublicacion;
    private String isbn;
    private String imagen;
    private Bitmap img;

    public Libro(Integer id, String titulo, String autor, String fechaPublicacion, String isbn, String imagen) {
        this(id,titulo,autor,fechaPublicacion,isbn,imagen,null);
    }

    public Libro(Integer id, String titulo, String autor, String fechaPublicacion, String isbn, String imagen, Bitmap img) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
        this.isbn = isbn;
        this.imagen = imagen;
        this.img = img;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


    public Libro(String titulo, String autor, String fechaPublicacion, String isbn,  String imagen) {
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
        this.isbn = isbn;
        this.imagen = imagen;

    }

    public Libro() {
    }


}
