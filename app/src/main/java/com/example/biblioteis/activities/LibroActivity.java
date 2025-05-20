package com.example.biblioteis.activities;

import android.os.Bundle;
import android.view.View;
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
import com.example.biblioteis.models.LibroDetalle;

public class LibroActivity extends AppCompatActivity {
    protected static final String BOOK_ID = "bookId";

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
        img = findViewById(R.id.imgDetalle);
        //OBTENER VM
        vm = new ViewModelProvider(this).get(DetalleVM.class);
        //OBSERVAR VM
        vm.librosLD.observe(this, libro ->{
            etTitulo.setText(libro.getTitulo());
            etAutor.setText(libro.getAutor());
            etFecha.setText(libro.getFecha());
            etISBN.setText(libro.getIsbn());
            etPrestamos.setText(libro.getPrestamos() + "");

            if(libro.getImg()!=null){
                img.setImageBitmap(libro.getImg());
            }else{
                img.setImageResource(R.drawable.icono);
            }

            if(libro.getEstado()){
                etEstado.setText("Disponible");
                btnPrestar.setVisibility(View.VISIBLE);
            }
            if(!libro.getEstado()){
                etEstado.setText("No Disponible");
            }
        });
        //VINCULAR ACCIONES
        int id = getIntent().getExtras().getInt(BOOK_ID);
        vm.load(id);
    }
}