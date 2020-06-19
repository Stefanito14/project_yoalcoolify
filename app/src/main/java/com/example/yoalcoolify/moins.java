package com.example.yoalcoolify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class moins extends AppCompatActivity {

    Button boutonRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moins);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        boutonRetour = findViewById(R.id.retour);
        bottomNavigationView.setSelectedItemId(R.id.moins);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.plus:
                        startActivity(new Intent(getApplicationContext(), plus.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.mid:
                        startActivity(new Intent(getApplicationContext(), sanction.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.moins:
                        return true;
                }
                return false;
            }
        });

        boutonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(moins.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
