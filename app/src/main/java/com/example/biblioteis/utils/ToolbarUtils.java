package com.example.biblioteis.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuProvider;

import com.example.biblioteis.R;
import com.example.biblioteis.activities.InicioActivity;
import com.example.biblioteis.activities.LibreriaActivity;
import com.example.biblioteis.activities.LibroActivity;
import com.example.biblioteis.activities.LogingActivity;
import com.example.biblioteis.activities.UsuarioActivity;

public class ToolbarUtils {


    public static void setupToolbar(AppCompatActivity activity) {
        Toolbar toolbar = activity.findViewById(R.id.libroToolbar);
        toolbar.setNavigationIcon(R.drawable.icono);
        activity.setSupportActionBar(toolbar);

        IPreferenciasUsuario prefs = new PreferenciasUsuario(activity);
        activity.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.menu_toolbar, menu);
                if (activity instanceof UsuarioActivity){
                    menu.findItem(R.id.opcion_usuario).setVisible(false);
                    toolbar.setTitle("Perfil");
                }
                if (activity instanceof LibreriaActivity){
                    menu.findItem(R.id.opcion_libreria).setVisible(false);
                    toolbar.setTitle("Listado Libros");
                }
                if (activity instanceof InicioActivity){
                    menu.findItem(R.id.opcion_inicio).setVisible(false);
                    toolbar.setTitle("Inicio");
                }
                if (activity instanceof LibroActivity){
                    toolbar.setTitle("Libro");
                }


            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.cerrar_sesion) {
                    prefs.borrar();

                    // Redirigir al usuario a la pantalla de login
                    activity.startActivity(new Intent(activity, LogingActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    return true;
                }

                if (menuItem.getItemId() == R.id.opcion_usuario) {
                    activity.startActivity(new Intent(activity, UsuarioActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    return true;
                }
                if (menuItem.getItemId() == R.id.opcion_libreria) {
                    activity.startActivity(new Intent(activity, LibreriaActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    return true;
                }
                if (menuItem.getItemId() == R.id.opcion_inicio) {
                    activity.startActivity(new Intent(activity, InicioActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    return true;
                }

                if (menuItem.getItemId() == R.id.opcion_qr) {
                    ScannerUtils.launchScanner(activity);
                    return true;
                }
                return false;
            }

        });
    }

}

