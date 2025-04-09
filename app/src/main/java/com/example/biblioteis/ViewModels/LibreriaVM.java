package com.example.biblioteis.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.biblioteis.models.Libro;
import com.example.biblioteis.models.LogingData;

public class LibreriaVM extends ViewModel {

    public MutableLiveData<Libro> libroLD= new MutableLiveData<Libro>();


}
