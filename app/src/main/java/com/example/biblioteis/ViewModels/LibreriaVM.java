package com.example.biblioteis.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.biblioteis.models.Libro;

public class LibreriaVM {

    private Libro libro;
    private MutableLiveData<Libro> libroMutableLiveData = new MutableLiveData<>();

    public LibreriaVM(Libro libro) {
        this.libro = libro;
    }
    public LiveData<Libro> getUserLiveData() {
        return libroMutableLiveData;
    }
    public void loadLibroData(int userId) {
        libro.getAutor();
        libro.getImagen();
        libro.getIsbn();
        libro.getFechaPublicacion();
        libro.getTitulo();
        libro.isEstado();
    }

}
