package com.example.biblioteis;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;

import java.util.ArrayList;
import java.util.List;

public class MyMenuProvider implements MenuProvider {

    List<Integer> bloquedItems = new ArrayList<>();

    Activity activity;

    public MyMenuProvider(Activity activity, List<Integer> bloquedItems){
        this.activity = activity;
        this.bloquedItems = bloquedItems;
    }

    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_toolbar, menu);

        for(Integer bloqued : bloquedItems){
            menu.findItem(bloqued).setVisible(false);
        }

    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.cerrar_sesion) {
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
        return false;
    }

}
