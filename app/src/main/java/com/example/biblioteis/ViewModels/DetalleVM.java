package com.example.biblioteis.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.biblioteis.models.Libro;

import java.util.List;

public class DetalleVM extends ViewModel {
    public MutableLiveData<Libro> librosLD= new MutableLiveData<>();
}
