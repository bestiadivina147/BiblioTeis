package com.example.biblioteis.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.biblioteis.models.Libro;

import java.util.ArrayList;
import java.util.List;

public class LibreriaVM extends ViewModel {

    public MutableLiveData<List<Libro>> librosLD= new MutableLiveData<>();


    public void filtrar(String autor, String titulo) {
        List<Libro> libros = new ArrayList<>();
        libros.add(new Libro("ppfiltro","pp","12/12/12","1212", "1.JPG"));
        libros.add(new Libro("ppfiltro","pp","12/12/12","1212", "1.JPG"));


        librosLD.setValue(libros);
    }

    public void loadLibros() {

        List<Libro> libros = new ArrayList<>();
        libros.add(new Libro("pp","pp","12/12/12","1212", "1.JPG"));
        libros.add(new Libro("pp","pp","12/12/12","1212", "1.JPG"));
        libros.add(new Libro("pp","pp","12/12/12","1212", "1.JPG"));
        libros.add(new Libro("pp","pp","12/12/12","1212", "1.JPG"));

        librosLD.setValue(libros);

    }
}
