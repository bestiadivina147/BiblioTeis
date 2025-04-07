package com.example.biblioteis;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuProvider;

import java.util.ArrayList;

public class ToolbarUtils {

    public static void setupToolbar(AppCompatActivity activity) {
        Toolbar toolbar = activity.findViewById(R.id.libroToolbar);
        toolbar.setNavigationIcon(R.drawable.icono);
        activity.setSupportActionBar(toolbar);
        activity.addMenuProvider(new MyMenuProvider(activity, new ArrayList<>()));
    }
}

