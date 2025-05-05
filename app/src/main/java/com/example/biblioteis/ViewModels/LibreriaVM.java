package com.example.biblioteis.ViewModels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.biblioteis.API.models.Book;
import com.example.biblioteis.API.repository.BookRepository;
import com.example.biblioteis.mapper.BookMapper;
import com.example.biblioteis.models.Libro;

import java.util.ArrayList;
import java.util.List;

public class LibreriaVM extends ViewModel {

    public MutableLiveData<List<Libro>> librosLD= new MutableLiveData<>();
    public BookRepository bookRepository ;


    public LibreriaVM() {
        this.bookRepository = new BookRepository();
    }

    public void filtrar(String autorFilter, String tituloFilter) {
        bookRepository.getBooks(new BookRepository.ApiCallback<List<Book>>() {
            @Override
            public void onSuccess(List<Book> result) {
                List<Libro> libros = new ArrayList<>();
                for (Book book : result ){
                    //Precondiciones
                    //Si se filtra por autor y el autor coincide exactamente
                    if(autorFilter != null && !autorFilter.equals("") && !autorFilter.equals(book.getAuthor())){
                        continue;
                    }
                    if(tituloFilter != null && !tituloFilter.equals("") && !tituloFilter.equals(book.getTitle())){
                        continue;
                    }

                    //Cuerpo objeto
                    libros.add(BookMapper.book2Libro(book));

                    //Post condiciones
                }
                librosLD.setValue(libros);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


    }

    public void loadLibros() {


        bookRepository.getBooks(new BookRepository.ApiCallback<List<Book>>() {
            @Override
            public void onSuccess(List<Book> result) {
                List<Libro> libros = new ArrayList<>();
                for (Book book : result ){
                    libros.add(BookMapper.book2Libro(book));
                }
                librosLD.setValue(libros);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("LibreriaVM", "Error load books", t);
            }
        });



    }
}
