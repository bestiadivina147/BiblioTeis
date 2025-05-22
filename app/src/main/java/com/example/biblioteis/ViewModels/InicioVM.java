package com.example.biblioteis.ViewModels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.biblioteis.API.models.Book;
import com.example.biblioteis.API.repository.BookRepository;
import com.example.biblioteis.API.repository.ImageRepository;
import com.example.biblioteis.mapper.BookMapper;
import com.example.biblioteis.models.Libro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.ResponseBody;

public class InicioVM extends ViewModel {
    public final MutableLiveData<List<Libro>> librosLD= new MutableLiveData<>();
    public BookRepository bookRepository ;
    public ImageRepository imageRepository= new ImageRepository();

    public InicioVM() {
        this.bookRepository = new BookRepository();
    }

    public void loadLibros() {
        bookRepository.getBooks(new BookRepository.ApiCallback<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                List<Libro> libros = new ArrayList<>();

                // Verificar que la lista no esté vacía
                if (books != null && !books.isEmpty()) {
                    Collections.shuffle(books); // Mezclar la lista aleatoriamente

                    // Seleccionar los primeros tres libros (o menos si hay menos de tres)
                    int cantidadLibros = Math.min(3, books.size());
                    for (int i = 0; i < cantidadLibros; i++) {
                        Libro l = BookMapper.book2Libro(books.get(i));
                        libros.add(l);
                        obtenerImagen(books.get(i), l, libros);
                    }
                }

                librosLD.setValue(libros);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("LibreriaVM", "Error load books", t);
            }
        });
    }

    private void obtenerImagen(Book book, Libro l, List<Libro> libros) {
        if(book.getBookPicture()==null) return ;
        imageRepository.getImage(book.getBookPicture(), new BookRepository.ApiCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                if(result==null)return;

                Bitmap bm = BitmapFactory.decodeStream(result.byteStream());
                l.setImg(bm);
                librosLD.setValue(libros);

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}
