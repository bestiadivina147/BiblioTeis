package com.example.biblioteis.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.biblioteis.API.models.Book;
import com.example.biblioteis.API.repository.BookRepository;
import com.example.biblioteis.mapper.BookMapper;
import com.example.biblioteis.models.Libro;
import com.example.biblioteis.models.LibroDetalle;

import java.util.List;

public class DetalleVM extends ViewModel {
    public MutableLiveData<LibroDetalle> librosLD= new MutableLiveData<>();
    public BookRepository bookRepository = new BookRepository();

    public void load(int id) {
        bookRepository.getBookById(id, new BookRepository.ApiCallback<Book>() {
            @Override
            public void onSuccess(Book result) {
                LibroDetalle libro = BookMapper.book2LibroDetalle(result);
                librosLD.setValue(libro);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
}
