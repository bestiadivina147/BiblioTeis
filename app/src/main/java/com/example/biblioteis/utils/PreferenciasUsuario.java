package com.example.biblioteis.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.biblioteis.models.LogingData;
import com.example.biblioteis.models.Usuario;

public class PreferenciasUsuario implements IPreferenciasUsuario {

    protected static final String MIS_PREFERENCIAS = "MisPreferencias";
    protected static final String USUARIO = "usuarioId";
    private SharedPreferences sharedPreferences;

    public PreferenciasUsuario(Context c) {
        this.sharedPreferences = c.getSharedPreferences(MIS_PREFERENCIAS, MODE_PRIVATE);
        Log.e("", "");
    }

    @Override
    public void guardarLoging(LogingData user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(USUARIO, user.getId());
        editor.apply();
    }

    @Override
    public int leer() {
        int usuarioGuardado = sharedPreferences.getInt(USUARIO, -1);
        return usuarioGuardado;
    }

    @Override
    public void borrar() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(USUARIO);
        editor.apply();
    }
}
