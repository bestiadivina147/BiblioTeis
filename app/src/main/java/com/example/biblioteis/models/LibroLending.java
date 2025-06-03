package com.example.biblioteis.models;


public class LibroLending {
    private int id;
    private int bookId;
    private Libro libro;
    private int userId;
    private String lendDate;
    private String returnDate;


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

    public LibroLending(int id, int bookId, int userId, String lendDate, String returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.lendDate = lendDate;
        this.returnDate = returnDate;
    }

    public LibroLending(int id, int bookId, Libro libro, int userId, String lendDate, String returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.libro = libro;
        this.userId = userId;
        this.lendDate = lendDate;
        this.returnDate = returnDate;
    }

}
