package com.example.biblioteis.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.biblioteis.models.LogingData;

public class LogingVM extends ViewModel {
    public MutableLiveData<LogingData> logingLD= new MutableLiveData<LogingData>();
    public void loging(String usuario, String contrasenha){
        if(usuario.equals("1234") && contrasenha.equals("1234")){
            logingLD.postValue(new LogingData(usuario, contrasenha,null));
        }
        else {
            logingLD.postValue(new LogingData(usuario, contrasenha,"Error al iniciar sesion"));
        }
    }
}
