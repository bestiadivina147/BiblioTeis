package com.example.biblioteis.ViewModels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.biblioteis.API.models.User;
import com.example.biblioteis.API.repository.BookRepository;
import com.example.biblioteis.API.repository.UserRepository;
import com.example.biblioteis.mapper.UserMapper;
import com.example.biblioteis.models.LogingData;

import java.util.ArrayList;
import java.util.List;

public class LogingVM extends ViewModel {
    public MutableLiveData<LogingData> logingLD= new MutableLiveData<LogingData>();
    public UserRepository userRepository;

    public LogingVM() {
        this.userRepository= new UserRepository();
    }

    public void loging(String usuario, String contrasenha){
        userRepository.login(usuario,contrasenha,new BookRepository.ApiCallback<User>() {
            @Override
            public void onSuccess(User result) {
                if(result == null){
                    LogingData ld = new LogingData("Error al iniciar sesion");
                    logingLD.setValue(ld);
                    return;
                }
                logingLD.setValue(UserMapper.user2LogingData(result));

            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("LoginVM", "error onFailure");
                LogingData ld = new LogingData("Error al iniciar sesion");
                logingLD.setValue(ld);
            }

        });

    }
}
