package com.example.biblioteis.utils;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.biblioteis.activities.LibroActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

public class ScannerUtils {
    private static final String CAMERA = Manifest.permission.CAMERA;
    private static final int REQUEST_CAMERA = 1234;

    /** Lanza el escáner ZXing desde cualquier AppCompatActivity. */
    public static void launchScanner(AppCompatActivity activity) {
        if (ContextCompat.checkSelfPermission(activity, CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            activity.requestPermissions(new String[]{ CAMERA }, REQUEST_CAMERA);
        } else {
            new IntentIntegrator(activity)
                    .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
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
        if (requestCode == REQUEST_CAMERA
                && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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

        String contents = data.getStringExtra("SCAN_RESULT");
        if (contents != null) {

                try {
                    // Interpretamos el contenido como ID de libro
                    int bookId = Integer.parseInt(contents);
                    Intent intent = new Intent(activity, LibroActivity.class);
                    intent.putExtra(LibroActivity.BOOK_ID, bookId);
                    activity.startActivity(intent);
                } catch (NumberFormatException e) {
                    Toast.makeText(activity,
                            "Código no válido: " + contents,
                            Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(activity,
                        "Escaneo cancelado",
                        Toast.LENGTH_SHORT).show();
            }
            return true;

    }
}
