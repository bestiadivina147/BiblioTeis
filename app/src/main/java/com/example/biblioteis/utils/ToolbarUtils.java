package com.example.biblioteis.utils;

import static android.content.Context.MODE_PRIVATE;

import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

public class ToolbarUtils {


    private static ActivityResultLauncher<Intent> qrLauncher;

    public static void setupToolbar(AppCompatActivity activity) {
        Toolbar toolbar = activity.findViewById(R.id.libroToolbar);
        toolbar.setNavigationIcon(R.drawable.icono);
        activity.setSupportActionBar(toolbar);

         qrLauncher = activity.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            Intent s = result.getData();
            ScannerUtils.onActivityResult(activity, 100, -1, s);
            if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                IntentResult scanResult = IntentIntegrator.parseActivityResult(result.getResultCode(), result.getData());
                if (scanResult != null && scanResult.getContents() != null) {
                    String qrCode = scanResult.getContents();
                    Toast.makeText(activity, "QR: " + qrCode, Toast.LENGTH_LONG).show();
                }
            }
        });

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


                    IntentIntegrator integrator = new IntentIntegrator(activity);

                    integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                    integrator.setPrompt("Apunta al código QR o de barras");
                    integrator.setBeepEnabled(true);
                    integrator.setOrientationLocked(false);
                    integrator.setCaptureActivity(CaptureActivity.class);
                    Intent intent = integrator.createScanIntent();

                    qrLauncher.launch(intent);

                    return true;
                }
                return false;
            }

        });
    }

}

