package com.example.dvdloc_weber;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Reservation extends AppCompatActivity {
    private Button btnConfirmer;
    private Button btnAnnulerResa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reservation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnConfirmer = findViewById(R.id.btnConfirmerResa);
        btnConfirmer.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Afficher le message Toast
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        btnAnnulerResa = findViewById(R.id.btnAnnuler);
        btnAnnulerResa.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        Button btnRetourReservations = (Button) findViewById(R.id.btnRetourReservations);

        btnRetourReservations.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Reservation.this);
                alertDialog.setTitle("Retour");
                alertDialog.setMessage("Voulez-vous revenir sur la page d'accueil ?");

                alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getApplicationContext(), btnRetourReservations.getText(), Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(Reservation.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

                alertDialog.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
                alertDialog.show();
            }
        });
    }
}