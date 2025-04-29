package com.example.dvdloc_weber;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Policier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_policier);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String titre = this.getIntent().getStringExtra("titre");
        TextView titreDemande = findViewById(R.id.titreDemande);
        titreDemande.setText(titre);

        Button btnRetourPoliciers = (Button) findViewById(R.id.btnRetourSeries);

        btnRetourPoliciers.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Policier.this);
                alertDialog.setTitle("Retour");
                alertDialog.setMessage("Voulez-vous revenir sur la page d'accueil ?");

                alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getApplicationContext(), btnRetourPoliciers.getText(), Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(Policier.this, MainActivity.class);
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

    public String filmsPoliciers[] =
            {
                    "Kill bill-Vol1",
                    "Kill bill-Vol2",
                    "Otage",
                    "Da Vinci Code",
                    "36 quai des Orfèvres",
                    "Mystic River"
            };

    final ArrayAdapter<String> adapterList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, filmsPoliciers);

    ListView listPolicier = (ListView) findViewById(R.id.policier_listview);
//    listPolicier.setAdapter(adapterList);
//
//    listPolicier.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//        public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
//            String titre = adapterList.getItem(position);
//            Toast toast = Toast.makeText(getApplicationContext(), "Position :" + String.valueOf(position), Toast.LENGTH_SHORT);
//            toast.show();
//            Toast toast2 = Toast.makeText(getApplicationContext(), "Titre :" + titre, Toast.LENGTH_SHORT);
//            toast2.show();
//        }
//    };
}