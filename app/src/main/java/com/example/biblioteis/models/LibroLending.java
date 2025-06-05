package com.example.biblioteis.models;


import com.example.biblioteis.utils.DateUtils;

import java.text.ParseException;
import java.util.Date;



public class LibroLending {

    protected static final int DIAS = 15;

    private int id;
    private int bookId;
    private Libro libro;
    private int userId;
    private String lendDate;
    private String returnDate;

    public LibroLending() {
    }

    public LibroLending(int id, int bookId, Libro libro, int userId, String lendDate, String returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.libro = libro;
        this.userId = userId;
        this.lendDate = lendDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLendDate() {
        return lendDate;
    }

    public void setLendDate(String lendDate) {
        this.lendDate = lendDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Date getExpectedReturnDate() {
        Date lendDate;
        try {
            lendDate = DateUtils.String2Date(this.lendDate);
        } catch (ParseException e) {
            try {
                lendDate = DateUtils.String2Date("9999/01/01'T'00:00:00");
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        return DateUtils.sumarDiasAFecha(lendDate, DIAS);
    }

    public EstadosDevolucion getEstadoDevolucion() {
        if (returnDate != null) {
            return EstadosDevolucion.DEVUELTO; // Si hay fecha de devolución, el libro fue devuelto.
        }

        // Comparar la fecha actual con la fecha esperada de devolución
        if (new Date().compareTo(this.getExpectedReturnDate()) > 0) {
            return EstadosDevolucion.ATRASADO; // Si la fecha actual es mayor que la esperada, está atrasado.
        }

        return EstadosDevolucion.ENPRESTAMO; // Si no ha sido devuelto y la fecha aún no ha pasado, sigue en préstamo.
    }

}
