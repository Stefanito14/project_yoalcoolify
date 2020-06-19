package com.example.yoalcoolify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bouton1, bouton2, bouton3, bouton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bouton1 = (Button)findViewById(R.id.button1);
        bouton2 = (Button)findViewById(R.id.button2);
        bouton3 = (Button)findViewById(R.id.button3);
        bouton4 = (Button)findViewById(R.id.button4);

        bouton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, taux.class);
                startActivity(i);
                finish();
            }
        });

        bouton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, sanction.class);
                startActivity(i);
                finish();
            }
        });



        bouton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Que voulez vous prendre ? ");
                builder.setCancelable(true);
                builder.setPositiveButton(
                        "Taxi",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Uri location = Uri.parse("geo:0,0?q=taxis");
                                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                                startActivity(mapIntent);
                            }
                        });

                builder.setNegativeButton(
                        "Bus",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Uri location = Uri.parse("geo:0,0?q=arret de bus");
                                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                                startActivity(mapIntent);
                            }
                        });

                AlertDialog alert11 = builder.create();
                alert11.show();

            }
        });

        bouton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, appel.class);
                startActivity(i);
                finish();
            }
        });

    }
}
