package com.example.biblioteis.mapper;

import com.example.biblioteis.API.models.BookLending;
import com.example.biblioteis.API.models.User;
import com.example.biblioteis.models.LibroLending;
import com.example.biblioteis.models.LogingData;
import com.example.biblioteis.models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static LogingData user2LogingData(User user){
        return new LogingData(user.getId(),user.getName(), user.getPasswordHash());
    }

    public static Usuario user2Usuario(User user){
        List<BookLending> listabook = user.getBookLendings();
        List<LibroLending> listaLibro = new ArrayList<>();
        for (BookLending l : listabook){
            LibroLending lib = BookMapper.booklending2LibroLending(l);
            listaLibro.add(lib);
        }
        return new Usuario(user.getId(), user.getName(), user.getEmail(), user.getDateJoined(),listaLibro);
    }
}
