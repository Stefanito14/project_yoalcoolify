package com.example.yoalcoolify;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;

import android.net.Uri;
import android.os.Bundle;

import android.view.ContextMenu;
import android.view.KeyEvent;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class appel extends AppCompatActivity {

    //private ArrayList<String> todoItems = new ArrayList<>();
    //private ArrayAdapter<String> aa;
    private NotesDbAdapter mDbHelper;
    private ListView maVariableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appel);

        // Ouverture bdd
        mDbHelper = new NotesDbAdapter(this);
        mDbHelper.open();
        fillData();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText maVariableEditText = (EditText) findViewById(R.id.editText);
        maVariableListView = (ListView) findViewById(R.id.listView);
        registerForContextMenu(maVariableListView);
        //maVariableListView.setAdapter(aa);
        maVariableEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
                        // todoItems.add(0, maVariableEditText.getText().toString());
                        // aa.notifyDataSetChanged();
                        String msg = maVariableEditText.getText().toString();
                        mDbHelper.createNote(msg, "");
                        fillData();
                        maVariableEditText.setText(""); // 3 - remise à vide de l'EditText
                        return true;
                    }
                return false;
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        maVariableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // todoItems.remove(position);
                //aa.notifyDataSetChanged();

                TextView tel = (TextView) findViewById(R.id.editText);
                String tele = tel.getText().toString();

                Intent callIntent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:" + tele));

                startActivity(callIntent);

                //mDbHelper.deleteNote(id);
                //fillData();

            }
        });



    }

    private void fillData() {
        final ListView maVariableListView = (ListView) findViewById(R.id.listView);
        // Get all of the notes from the database and create the item list
        Cursor c = mDbHelper.fetchAllNotes();
        startManagingCursor(c);

        String[] from = new String[]{NotesDbAdapter.KEY_TITLE};
        int[] to = new int[]{R.id.text1};

        // Now create an array adapter and set it to display using our row
        SimpleCursorAdapter notes =
                new SimpleCursorAdapter(this, R.layout.notes_row, c, from, to);
        maVariableListView.setAdapter(notes);
    }

    /* Function called when the user clicked on the enter button */
    public void sendMessage(View view) {

        final EditText maVariableEditText = (EditText) findViewById(R.id.editText);
        String msg = maVariableEditText.getText().toString();
        mDbHelper.createNote(msg, "");
        fillData();
        maVariableEditText.setText("");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.action_settings:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Voulez vous tout supprimer ? ");
                builder.setCancelable(true);
                builder.setPositiveButton(
                        "Oui",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // todoItems.clear();
                                //  aa.notifyDataSetChanged();
                                mDbHelper.deleteAll();
                                fillData();
                            }
                        });

                builder.setNegativeButton(
                        "Non",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder.create();
                alert11.show();
            /*
            todoItems.clear();
            aa.notifyDataSetChanged();
            */
                return true;
            case R.id.retour_Acceuil:
                Intent i = new Intent(appel.this, MainActivity.class);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }



    }




}