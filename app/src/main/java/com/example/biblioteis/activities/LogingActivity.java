package com.example.biblioteis.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.biblioteis.R;
import com.example.biblioteis.ViewModels.LogingVM;

public class LogingActivity extends AppCompatActivity {
    private EditText usuarioET, contrasenhaET;
    private Button btnLogin;
    private TextView errorTV;
    private LogingVM vm;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loging);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usuarioET = findViewById(R.id.etEmail);
        contrasenhaET = findViewById(R.id.etContra);
        errorTV = findViewById(R.id.txtLoginError);
        btnLogin = findViewById(R.id.btnLogin);
        vm = new ViewModelProvider(this).get(LogingVM.class);

        sharedPreferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);

        // Recuperar usuario y contraseña guardados
        String usuarioGuardado = sharedPreferences.getString("usuario", "");
        String contrasenhaGuardada = sharedPreferences.getString("contrasenha", "");

        if (!usuarioGuardado.isEmpty() && !contrasenhaGuardada.isEmpty()) {
            vm.loging(usuarioGuardado,contrasenhaGuardada);
        }

        vm.logingLD.observe(this, (logingData) -> {
            if (logingData.getMensaje() != null) {
                errorTV.setVisibility(View.VISIBLE);
                errorTV.setText(logingData.getMensaje());
            }
            else{
                errorTV.setVisibility(View.INVISIBLE);
                Toast.makeText(LogingActivity.this, "Login correcto", Toast.LENGTH_SHORT).show();

                // Guardar usuario y contraseña en SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("usuario", usuarioET.getText().toString());
                editor.putString("contrasenha", contrasenhaET.getText().toString());
                editor.apply();
                Intent intent = new Intent(LogingActivity.this, InicioActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(l -> {
            vm.loging(usuarioET.getText().toString(), contrasenhaET.getText().toString());
        });
    }
}
