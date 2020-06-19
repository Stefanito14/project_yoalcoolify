package com.example.yoalcoolify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.system.Os;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.text.DecimalFormat;
import java.util.MissingFormatArgumentException;

public class taux extends AppCompatActivity {

    CheckBox check_M,check_F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taux);
        final Integer[] nbr_verres = new Integer[]{0,1,2,3,4,5,6,7,8,9};
        final Spinner list = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, nbr_verres);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        list.setAdapter(adapter);
        final Button calcul = (Button) findViewById(R.id.btn_calcul);
        final EditText edit_poids = (EditText) findViewById (R.id.poids);
        final ImageView logo = findViewById(R.id.imgLogo);


        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(taux.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        });

        calcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(taux.this);
                DecimalFormat df = new DecimalFormat("0.##");
                double taux;

                int nbr_verre = (int) list.getSelectedItem();
                Integer poids = Integer.parseInt(String.valueOf(edit_poids.getText()));
                check_M = findViewById(R.id.checkBox_Masculin);
                check_F = findViewById(R.id.checkBox_Feminin);
                if(check_M.isChecked() && check_F.isChecked()){

                    builder.setMessage("Sélectionner seulement un sexe");
                    builder.setCancelable(true);
                    AlertDialog alert11 = builder.create();
                    alert11.show();
                }
                if(check_M.isChecked() == false && check_F.isChecked() == false) {

                    builder.setMessage("Sélectionner au moins un sexe");
                    builder.setCancelable(true);
                    AlertDialog alert11 = builder.create();
                    alert11.show();

                }
                if(check_M.isChecked() && check_F.isChecked() == false){

                    taux = (nbr_verre*10)/(poids*0.7);

                    builder.setMessage("Votre taux d'alcool est de " + df.format(taux) + "g/l");
                    builder.setCancelable(true);

                    final double finalTaux = taux;
                    builder.setPositiveButton(
                            "Voir sanctions",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    if(finalTaux <0.5){

                                        Intent i = new Intent(taux.this, moins.class);
                                        startActivity(i);
                                        finish();

                                    }
                                    else if(finalTaux>0.5 && finalTaux<0.8){

                                        Intent i = new Intent(taux.this, sanction.class);
                                        startActivity(i);
                                        finish();

                                    }
                                    else if(finalTaux>0.8){

                                        Intent i = new Intent(taux.this, plus.class);
                                        startActivity(i);
                                        finish();

                                    }


                                }
                            });



                    AlertDialog alert11 = builder.create();
                    alert11.show();

                }
                if(check_F.isChecked() && check_M.isChecked() == false){

                    taux = (nbr_verre*10)/(poids*0.6);

                    builder.setMessage("Votre taux d'alcool est de " + df.format(taux) + "g/l");
                    builder.setCancelable(true);

                    final double finalTaux = taux;
                    builder.setPositiveButton(
                            "Voir sanctions",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    if(finalTaux <0.5){

                                        Intent i = new Intent(taux.this, moins.class);
                                        startActivity(i);
                                        finish();

                                    }
                                    else if(finalTaux>0.5 && finalTaux<0.8){

                                        Intent i = new Intent(taux.this, sanction.class);
                                        startActivity(i);
                                        finish();

                                    }
                                    else if(finalTaux>0.8){

                                        Intent i = new Intent(taux.this, plus.class);
                                        startActivity(i);
                                        finish();

                                    }

                                }

                            });

                    AlertDialog alert11 = builder.create();
                    alert11.show();

                }

            }

        });

    }
}
