package com.example.biblioteis.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biblioteis.R;
import com.example.biblioteis.ToolbarUtils;
import com.example.biblioteis.ViewModels.LibreriaVM;
import com.example.biblioteis.models.Libro;

public class LibreriaActivity extends AppCompatActivity {
    private EditText etAutor, etTitulo;
    private Button btnFiltrar;
    private RecyclerView rv;
    private LibreriaVM vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_libreria);
        ToolbarUtils.setupToolbar(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //VINCULAR ELEMENTOS GRAFICOS
        etAutor = findViewById(R.id.etfiltroautor);
        etTitulo = findViewById(R.id.etfiltrotitulo);
        btnFiltrar = findViewById(R.id.btnFiltrar);
        rv = findViewById(R.id.rvLibros);
//        OBTENER VM
        vm = new ViewModelProvider(this).get(LibreriaVM.class);
//        OBSERVAR VM
        vm.librosLD.observe(this, libros -> {

            //actualizamos el rv
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(new RecyclerView.Adapter() {

                class LibroViewHolder extends RecyclerView.ViewHolder {

                    public TextView tvAutor, tvTitulo, tvFecha, tvDisponibles, tvTotales;
                    public ImageView imgLibro;
                    public Button btnVerMas;

                    public LibroViewHolder(@NonNull View iv) {
                        super(iv);

                        tvAutor = iv.findViewById(R.id.tvFrAutor);
                        tvTitulo = iv.findViewById(R.id.tvFrTitulo);
                        tvFecha = iv.findViewById(R.id.tvFrFecha);
                        tvDisponibles = iv.findViewById(R.id.tvFrDisponibles);
                        tvTotales = iv.findViewById(R.id.tvFrTotales);

                        imgLibro = iv.findViewById(R.id.imgFrLibro);
                        btnVerMas = iv.findViewById(R.id.btnVerMas);
                    }
                }

                @NonNull
                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_libro, parent, false);

                    return new LibroViewHolder(view);
                }

                @Override
                public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                    Libro libro = libros.get(position);
                    LibroViewHolder lvh = (LibroViewHolder) holder;

                    lvh.tvAutor.setText(libro.getAutor());
                    lvh.tvTitulo.setText(libro.getTitulo());
                    lvh.tvFecha.setText(libro.getFechaPublicacion());
                    lvh.tvDisponibles.setText(0 + "");
                    lvh.tvTotales.setText(0 + "");

                    lvh.btnVerMas.setOnClickListener(l->{
                        Intent intent = new Intent(LibreriaActivity.this, LibroActivity.class);
                        intent.putExtra(LibroActivity.BOOK_ID, libro.getId());
                        startActivity(intent);
                    });

                }

                @Override
                public int getItemCount() {
                    return libros.size();
                }
            });

        });
//        VINCULAR ACCIONES
        btnFiltrar.setOnClickListener(l -> {
            String autor = etAutor.getText().toString();
            String titulo = etTitulo.getText().toString();
            vm.filtrar(autor, titulo);
        });

        vm.loadLibros();
    }


}