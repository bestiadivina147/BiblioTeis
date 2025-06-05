package com.example.biblioteis.mapper;

import com.example.biblioteis.API.models.Book;
import com.example.biblioteis.API.models.BookLending;
import com.example.biblioteis.models.Libro;
import com.example.biblioteis.models.LibroDetalle;
import com.example.biblioteis.models.LibroLending;
import com.example.biblioteis.utils.DateUtils;

import java.text.ParseException;
import java.util.Date;

public class BookMapper {


    public static Libro book2Libro(Book book){

        return new Libro(book.getId(),book.getTitle(),book.getAuthor(),book.getPublishedDate(),book.getIsbn(),book.getBookPicture());

    }

    public static LibroLending booklending2LibroLending (BookLending lending){
        Libro l = book2Libro(lending.getBook());

        return new LibroLending(lending.getId(),lending.getBookId(), l , lending.getUserId(),lending.getLendDate(),lending.getReturnDate());
    }

    public static LibroDetalle book2LibroDetalle(Book book) {
        BookLending bookLendingOwner = null;

        for (BookLending l : book.getBookLendings()) {
            if (l.getReturnDate() == null) {
                bookLendingOwner = l;
                break;
            }
        }

        int uid = bookLendingOwner != null ? bookLendingOwner.getUserId() : -1;
        //Libro no encontrado
        return new LibroDetalle(book.getBookPicture(), book.getTitle(), book.getAuthor(), book.getPublishedDate(), book.getIsbn(), book.isAvailable(), book.getBookLendings().size(), uid);

    }

}
