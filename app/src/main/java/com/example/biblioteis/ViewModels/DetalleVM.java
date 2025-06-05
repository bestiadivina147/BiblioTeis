package com.example.biblioteis.ViewModels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.biblioteis.API.models.Book;
import com.example.biblioteis.API.models.BookLending;
import com.example.biblioteis.API.repository.BookLendingRepository;
import com.example.biblioteis.API.repository.BookRepository;
import com.example.biblioteis.API.repository.ImageRepository;
import com.example.biblioteis.mapper.BookMapper;
import com.example.biblioteis.models.Libro;
import com.example.biblioteis.models.LibroDetalle;

import java.util.List;

import okhttp3.ResponseBody;

public class DetalleVM extends ViewModel {
    public MutableLiveData<LibroDetalle> librosLD= new MutableLiveData<>();

    public BookRepository bookRepository = new BookRepository();
    public ImageRepository imageRepository = new ImageRepository();
    public BookLendingRepository bookLendingRepository = new BookLendingRepository();

    public void load(int id) {
        bookRepository.getBookById(id, new BookRepository.ApiCallback<Book>() {
            @Override
            public void onSuccess(Book result) {
                LibroDetalle libro = BookMapper.book2LibroDetalle(result);
                librosLD.setValue(libro);
                obtenLibroImagen(result.getBookPicture(), libro);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    private void obtenLibroImagen(String urlImg, LibroDetalle libro) {
        if(urlImg==null){
            return;
        }
        imageRepository.getImage(urlImg, new BookRepository.ApiCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                if (result == null){
                    return;
                }
                Bitmap bm = BitmapFactory.decodeStream(result.byteStream());
                libro.setImg(bm);
                librosLD.postValue(libro);
            }

            @Override
            public void onFailure(Throwable t) {
                //TODO
                Log.e("LibreriaVM", "Error load books", t);
            }
        });
    }
}
