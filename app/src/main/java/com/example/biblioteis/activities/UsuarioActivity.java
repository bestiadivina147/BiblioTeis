package com.example.biblioteis.activities;

import android.annotation.SuppressLint;
import android.content.Context;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biblioteis.R;
import com.example.biblioteis.ViewModels.UsuarioVM;
import com.example.biblioteis.models.EstadosDevolucion;
import com.example.biblioteis.models.Libro;
import com.example.biblioteis.models.LibroLending;
import com.example.biblioteis.utils.IPreferenciasUsuario;
import com.example.biblioteis.utils.PreferenciasUsuario;
import com.example.biblioteis.utils.ToolbarUtils;

import java.util.List;

public class UsuarioActivity extends AppCompatActivity {

    private TextView txtNombre,txtCorreo,txtFecha;
    private RecyclerView rv;
    private UsuarioVM vm;



    private IPreferenciasUsuario prefs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_usuario);
        ToolbarUtils.setupToolbar(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        prefs = new PreferenciasUsuario(this);
        //VINCULAR ELEMENTOS GRAFICOS
        txtNombre = findViewById(R.id.txtNombre);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtFecha = findViewById(R.id.txtFecha);
        rv = findViewById(R.id.rvLibrosUsuario);
        //OBTENER VM
        vm = new ViewModelProvider(this).get(UsuarioVM.class);
        //OBSERVAR VM
        vm.usuarioLD.observe(this,usuario -> {
            List <LibroLending> libroLendings = usuario.getLibros();



            txtNombre.setText(usuario.getNombre());
            txtCorreo.setText(usuario.getCorreo());
            txtFecha.setText(usuario.getFecha());

            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(new RecyclerView.Adapter() {

                class LibroViewHolder extends RecyclerView.ViewHolder {

                    public TextView tvAutor, tvTitulo, tvFecha, tvDisponibles, tvTotales;
                    public ImageView imgLibro;
                    public Button btnVerMas,btndispo;

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
                        btndispo = iv.findViewById(R.id.btndispo);
                    }
                }

                @NonNull
                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_librouser, parent, false);

                    return new LibroViewHolder(view);
                }

                @Override
                public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                    LibroLending libroLending = libroLendings.get(position);
                    LibroViewHolder lvh = (LibroViewHolder) holder;

                    Libro libro = libroLending.getLibro();

                    lvh.tvAutor.setText(libro.getAutor());
                    lvh.tvTitulo.setText(libro.getTitulo());
                    lvh.tvFecha.setText(libro.getFechaPublicacion());
                    lvh.tvTotales.setText(0 + "");
                    if(libro.getImg()!=null){
                        lvh.imgLibro.setImageBitmap(libro.getImg());
                    }else{
                        lvh.imgLibro.setImageResource(R.drawable.icono);
                    }

                    //Gestionar elementos visuales en base a su estado de devolucion
                    gestionarEstadoDevolucion(libroLending,lvh);


                    lvh.btnVerMas.setOnClickListener(l->{
                        Intent intent = new Intent(UsuarioActivity.this, LibroActivity.class);
                        intent.putExtra(LibroActivity.BOOK_ID, libro.getId());
                        startActivity(intent);
                    });
                    lvh.cl.setOnClickListener(l->{
                        Intent intent = new Intent(UsuarioActivity.this, LibroActivity.class);
                        intent.putExtra(LibroActivity.BOOK_ID, libro.getId());
                        startActivity(intent);
                    });
                }



                @Override
                public int getItemCount() {
                    return libroLendings.size();
                }


                private void gestionarEstadoDevolucion(LibroLending libroLending, LibroViewHolder lvh) {
                    EstadosDevolucion ed = libroLending.getEstadoDevolucion();

                    // Obtener contexto desde la vista del ViewHolder
                    Context context = lvh.itemView.getContext();

                    // Obtener el color correcto desde colors.xml
                    int color;
                    switch (ed) {
                        case DEVUELTO:
                            color = ContextCompat.getColor(context, R.color.succes); // Verde para libros devueltos
                            lvh.btndispo.setText("Devuelto");
                            break;

                        case ATRASADO:
                            color = ContextCompat.getColor(context, R.color.danger); // Rojo para libros atrasados
                            lvh.btndispo.setText("Atrasado");
                            break;

                        case ENPRESTAMO:
                            color = ContextCompat.getColor(context, R.color.warning); // Amarillo para libros en préstamo
                            lvh.btndispo.setText("En préstamo");
                            break;

                        default:
                            color = ContextCompat.getColor(context, R.color.gris); // Gris para estados desconocidos
                            lvh.btndispo.setText("Desconocido");
                            break;
                    }

                    // Aplicar el color de fondo al botón
                    lvh.btndispo.setBackgroundColor(color);
                }



            });
        });

        //VINCULAR ACCIONES
        int id = prefs.leer();
        vm.load(id);
    }
}