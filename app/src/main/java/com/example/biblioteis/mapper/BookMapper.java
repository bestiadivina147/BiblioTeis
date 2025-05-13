package com.example.biblioteis.mapper;

import com.example.biblioteis.API.models.Book;
import com.example.biblioteis.API.models.BookLending;
import com.example.biblioteis.models.Libro;
import com.example.biblioteis.models.LibroDetalle;

public class BookMapper {

    public static Libro book2Libro(Book book){

        return new Libro(book.getId(),book.getTitle(),book.getAuthor(),book.getPublishedDate(),book.getIsbn(),book.getBookPicture());

    }

    public static LibroDetalle book2LibroDetalle(Book book){
        BookLending bookLendingOwner = null;

        for (BookLending l: book.getBookLendings()) {
            if(l.getReturnDate() == null){
                bookLendingOwner = l;
                break;
            }
        }
        
//        if(bookLendingOwner == null){
//            
//            //Libro no encontrado
//        }
        
        
        
        return new LibroDetalle(book.getBookPicture(),book.getTitle(), book.getAuthor(),book.getPublishedDate(),book.getIsbn(),book.isAvailable(),book.getBookLendings().size(),bookLendingOwner.getUserId());
    }
}
