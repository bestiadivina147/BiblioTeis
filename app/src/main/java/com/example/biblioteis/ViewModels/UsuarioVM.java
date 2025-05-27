package com.example.biblioteis.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.biblioteis.API.models.Book;
import com.example.biblioteis.API.models.User;
import com.example.biblioteis.API.repository.BookRepository;
import com.example.biblioteis.API.repository.UserRepository;
import com.example.biblioteis.mapper.BookMapper;
import com.example.biblioteis.models.LibroDetalle;
import com.example.biblioteis.models.Usuario;

public class UsuarioVM extends ViewModel {
    public MutableLiveData<Usuario> usuarioLD= new MutableLiveData<>();
    public UserRepository userRepository = new UserRepository();

    public void load(int id) {
        userRepository.getUserById(id, new BookRepository.ApiCallback<User>() {
            @Override
            public void onSuccess(User result) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
}
