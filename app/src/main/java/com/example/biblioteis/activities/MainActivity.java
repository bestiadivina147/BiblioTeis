package com.example.biblioteis.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.biblioteis.R;
import com.example.biblioteis.utils.IPreferenciasUsuario;
import com.example.biblioteis.utils.PreferenciasUsuario;
import com.example.biblioteis.utils.ScannerUtils;

public class MainActivity extends AppCompatActivity {

    private IPreferenciasUsuario prefs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new PreferenciasUsuario(this);
        if(prefs.leer()!=-1){
            startActivity(new Intent(this, InicioActivity.class));
        }
        else{
            startActivity(new Intent(this, LogingActivity.class));
        }
    }

}