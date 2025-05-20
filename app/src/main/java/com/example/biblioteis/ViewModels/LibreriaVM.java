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
import com.example.biblioteis.models.LibroDetalle;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class LibreriaVM extends ViewModel {

    public final MutableLiveData<List<Libro>> librosLD= new MutableLiveData<>();
    public BookRepository bookRepository ;
    public ImageRepository imageRepository= new ImageRepository();


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

                    Libro l = BookMapper.book2Libro(book);
                    obtenerImagen(book, l, libros);
                    libros.add(l);

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
            public void onSuccess(List<Book> books) {
                List<Libro> libros = new ArrayList<>();
                for (Book book : books ){
                    Libro l = BookMapper.book2Libro(book);
                    libros.add(l);

                    obtenerImagen(book, l, libros);


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
