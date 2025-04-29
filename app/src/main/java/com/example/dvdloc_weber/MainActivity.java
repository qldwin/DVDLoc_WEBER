package com.example.dvdloc_weber;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ActivityResultLauncher<Intent> reservationLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK) {
                            Toast.makeText(MainActivity.this, "Réservation Confirmée", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        Button btnPolicier = findViewById(R.id.btnPolicier);
        btnPolicier.setOnClickListener(v -> {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle("Policier");
            alertDialog.setMessage("Voulez-vous choisir un film Policier ?");

            alertDialog.setPositiveButton("Oui", (dialog, which) -> {
                Toast.makeText(getApplicationContext(), btnPolicier.getText(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, Policier.class);
                startActivity(intent);
            });

            alertDialog.setNegativeButton("Non", (dialog, which) -> {});
            alertDialog.show();
        });

        Button btnFiction = findViewById(R.id.btnFiction);
        btnFiction.setOnClickListener(v -> {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle("Fiction");
            alertDialog.setMessage("Voulez-vous choisir un film Fiction ?");

            alertDialog.setPositiveButton("Oui", (dialog, which) -> {
                Toast.makeText(getApplicationContext(), btnFiction.getText(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, Fiction.class);
                startActivity(intent);
            });

            alertDialog.setNegativeButton("Non", (dialog, which) -> {});
            alertDialog.show();
        });

        Button btnDocu = findViewById(R.id.btnDocu);
        btnDocu.setOnClickListener(v -> {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle("Documentaire");
            alertDialog.setMessage("Voulez-vous choisir un Documentaire ?");

            alertDialog.setPositiveButton("Oui", (dialog, which) -> {
                Toast.makeText(getApplicationContext(), btnDocu.getText(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, Documentaire.class);
                startActivity(intent);
            });

            alertDialog.setNegativeButton("Non", (dialog, which) -> {});
            alertDialog.show();
        });

        Button btnSerie = findViewById(R.id.btnSerie);
        btnSerie.setOnClickListener(v -> {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle("Série");
            alertDialog.setMessage("Voulez-vous choisir une Série ?");

            alertDialog.setPositiveButton("Oui", (dialog, which) -> {
                Toast.makeText(getApplicationContext(), btnSerie.getText(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, Serie.class);
                startActivity(intent);
            });

            alertDialog.setNegativeButton("Non", (dialog, which) -> {});
            alertDialog.show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menugeneral, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menuRechercher:
                Log.i("LocDVD", "Menu: Rechercher un film");
                Intent intentRechercher = new Intent(MainActivity.this, Recherche.class);
                rechercheLauncher.launch(intentRechercher);
                return true;
            case R.id.menuReserver:
                Log.i("LocDVD", "Menu: Reserver un film");
                Intent intentReserver = new Intent(MainActivity.this, Reservation.class);
                reservationLauncher.launch(intentReserver);
                return true;
            case R.id.menuMagasin:
                Log.i("LocDVD", "Menu: Magasin");
            case R.id.menuContact:
                Log.i("LocDVD", "Menu: Contact");
                return true;
        }
        return true;
    }
    ActivityResultLauncher<Intent> reservationLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Toast.makeText(MainActivity.this, "Réservation confirmée !", Toast.LENGTH_SHORT).show();
                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        Toast.makeText(MainActivity.this, "Reservation annulée !", Toast.LENGTH_SHORT).show();
                    }
                 }
            });

    ActivityResultLauncher<Intent> rechercheLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String titre = data.getStringExtra("titre");
                        Toast.makeText(MainActivity.this, titre, Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );
}
