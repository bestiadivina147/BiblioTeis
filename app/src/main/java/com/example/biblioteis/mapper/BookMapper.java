package com.example.biblioteis.mapper;

import com.example.biblioteis.API.models.Book;
import com.example.biblioteis.models.Libro;

public class BookMapper {

    public static Libro book2Libro(Book book){

        return new Libro(book.getTitle(),book.getAuthor(),book.getPublishedDate(),book.getIsbn(),book.getBookPicture());

    }
}
