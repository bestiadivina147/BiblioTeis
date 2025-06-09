package com.example.biblioteis.utils;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;




public class ScannerUtils {
    private static final String CAMERA = Manifest.permission.CAMERA;

    /** Lanza el escáner ZXing desde cualquier AppCompatActivity. */
    public static void launchScanner(AppCompatActivity activity) {
        if (ContextCompat.checkSelfPermission(activity, CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Pide permiso y al conceder relanza el escáner:
            activity.requestPermissions(new String[]{ CAMERA }, 1234);
        } else {
            new IntentIntegrator(activity)
                    .setPrompt("Apunta al código QR o de barras")
                    .setBeepEnabled(true)
                    .setOrientationLocked(false)
                    .setCaptureActivity(CaptureActivity.class)
                    .initiateScan();
        }
    }

    /** Debe invocarse desde onRequestPermissionsResult del Activity */
    public static void onRequestPermissionsResult(AppCompatActivity activity,
                                                  int requestCode,
                                                  String[] permissions,
                                                  int[] grantResults) {
        if (requestCode == 1234
                && grantResults.length>0
                && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
            launchScanner(activity);
        } else {
            Toast.makeText(activity,
                    "Permiso de cámara denegado", Toast.LENGTH_SHORT).show();
        }
    }

    /** Debe invocarse desde onActivityResult del Activity */
    public static boolean onActivityResult(AppCompatActivity activity,
                                           int requestCode,
                                           int resultCode,
                                           Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                Toast.makeText(activity,
                        "Escaneado: " + result.getContents(),
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(activity,
                        "Escaneo cancelado", Toast.LENGTH_SHORT).show();
            }
            return true;  // manejado
        }
        return false;     // no era nuestro escaneo
    }
}
