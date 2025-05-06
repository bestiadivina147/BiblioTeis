package com.example.biblioteis.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.biblioteis.R;
import com.example.biblioteis.ToolbarUtils;
import com.example.biblioteis.ViewModels.DetalleVM;
import com.example.biblioteis.ViewModels.LibreriaVM;

public class LibroActivity extends AppCompatActivity {

    private ImageView img;
    private TextView etTitulo, etAutor, etFecha,etISBN,etEstado,etPrestamos,txtNoDisponible;
    private Button btnPrestar,btnDevolver;
    private DetalleVM vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_libro);
        ToolbarUtils.setupToolbar(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //VINCULAR ELEMENTOS GRAFICOS
        etTitulo = findViewById(R.id.etTitulo);
        etAutor = findViewById(R.id.etAutor);
        etFecha = findViewById(R.id.etFecha);
        etISBN = findViewById(R.id.etISBN);
        etEstado = findViewById(R.id.etEstado);
        etPrestamos = findViewById(R.id.etPrestamos);
        txtNoDisponible = findViewById(R.id.txtNoDisponible);
        btnPrestar = findViewById(R.id.btnPrestar);
        btnDevolver = findViewById(R.id.btnDevolver);
        //OBTENER VM
        vm = new ViewModelProvider(this).get(DetalleVM.class);
        //OBSERVAR VM
        vm.librosLD.observe(this, libros ->{
//            etTitulo.setText(libro.getAutor());
//            lvh.tvTitulo.setText(libro.getTitulo());
//            lvh.tvFecha.setText(libro.getFechaPublicacion());
//            lvh.tvDisponibles.setText(0 + "");
//            lvh.tvTotales.setText(0 + "");

        });
        //VINCULAR ACCIONES

    }
}