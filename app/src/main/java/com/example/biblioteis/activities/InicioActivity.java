package com.example.biblioteis.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biblioteis.R;
import com.example.biblioteis.utils.DateUtils;
import com.example.biblioteis.utils.ScannerUtils;
import com.example.biblioteis.utils.ToolbarUtils;
import com.example.biblioteis.ViewModels.InicioVM;
import com.example.biblioteis.models.Libro;

public class InicioActivity extends AppCompatActivity {
    private RecyclerView rv;
    private InicioVM vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);
        ToolbarUtils.setupToolbar(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //VINCULAR ELEMENTOS GRAFICOS
        rv = findViewById(R.id.rvLibrosInicio);
        //        OBTENER VM
        vm = new ViewModelProvider(this).get(InicioVM.class);
        //        OBSERVAR VM
        vm.librosLD.observe(this, libros -> {
            //actualizamos el rv
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(new RecyclerView.Adapter(){

                class LibroViewHolder extends RecyclerView.ViewHolder {

                    public TextView tvAutor, tvTitulo, tvFecha, tvDisponibles, tvTotales;
                    public ImageView imgLibro;
                    public Button btnVerMas;

                    public ConstraintLayout cl;

                    public LibroViewHolder(@NonNull View iv) {
                        super(iv);

                        tvAutor = iv.findViewById(R.id.tvFrAutor);
                        tvTitulo = iv.findViewById(R.id.tvFrTitulo);
                        tvFecha = iv.findViewById(R.id.tvFrFecha);
                        tvDisponibles = iv.findViewById(R.id.tvFrDisponibles);
                        tvTotales = iv.findViewById(R.id.tvFrTotales);

                        imgLibro = iv.findViewById(R.id.imgFrLibro);
                        cl = iv.findViewById(R.id.libroConstraint);
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
                    String fechasinformatear = libro.getFechaPublicacion();
                    String fecha = DateUtils.formatDate(fechasinformatear);
                    lvh.tvAutor.setText(libro.getAutor());
                    lvh.tvTitulo.setText(libro.getTitulo());
                    lvh.tvFecha.setText(fecha);
                    lvh.tvDisponibles.setText(0 + "");
                    lvh.tvTotales.setText(0 + "");
                    if(libro.getImg()!=null){
                        lvh.imgLibro.setImageBitmap(libro.getImg());
                    }else{
                        lvh.imgLibro.setImageResource(R.drawable.icono);
                    }

                    lvh.btnVerMas.setOnClickListener(l->{
                        Intent intent = new Intent(InicioActivity.this, LibroActivity.class);
                        intent.putExtra(LibroActivity.BOOK_ID, libro.getId());
                        startActivity(intent);
                    });
                    lvh.cl.setOnClickListener(l->{
                        Intent intent = new Intent(InicioActivity.this, LibroActivity.class);
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
        //VINCULAR ACCIONES
        vm.loadLibros();
    }
}