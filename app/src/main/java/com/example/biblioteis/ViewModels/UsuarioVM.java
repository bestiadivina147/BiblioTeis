package com.example.biblioteis.ViewModels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.biblioteis.API.models.Book;
import com.example.biblioteis.API.models.User;
import com.example.biblioteis.API.repository.BookRepository;
import com.example.biblioteis.API.repository.ImageRepository;
import com.example.biblioteis.API.repository.UserRepository;
import com.example.biblioteis.mapper.BookMapper;
import com.example.biblioteis.mapper.UserMapper;
import com.example.biblioteis.models.Libro;
import com.example.biblioteis.models.LibroDetalle;
import com.example.biblioteis.models.Usuario;

import okhttp3.ResponseBody;

public class UsuarioVM extends ViewModel {
    public MutableLiveData<Usuario> usuarioLD= new MutableLiveData<>();
    public MutableLiveData<Libro> librosLD= new MutableLiveData<>();
    public UserRepository userRepository = new UserRepository();
    public BookRepository bookRepository = new BookRepository();
    public ImageRepository imageRepository = new ImageRepository();

    public void load(int id) {
        userRepository.getUserById(id, new BookRepository.ApiCallback<User>() {
            @Override
            public void onSuccess(User result) {
                Usuario usuario = UserMapper.user2Usuario(result);
                usuarioLD.setValue(usuario);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("UsuarioVM", "Error al obtener usuario", t);
            }
        });

    }


    public void loadLibro(int id) {
        bookRepository.getBookById(id, new BookRepository.ApiCallback<Book>() {
            @Override
            public void onSuccess(Book result) {
                Libro libro = BookMapper.book2Libro(result);
                librosLD.setValue(libro);
                obtenLibroImagen(result.getBookPicture(), libro);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    private void obtenLibroImagen(String urlImg, Libro libro) {
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
