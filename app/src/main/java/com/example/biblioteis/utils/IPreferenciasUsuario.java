package com.example.biblioteis.utils;

import com.example.biblioteis.models.LogingData;
import com.example.biblioteis.models.Usuario;

public interface IPreferenciasUsuario {
    void guardarLoging(LogingData l);
    int leer();
    void borrar();
}
