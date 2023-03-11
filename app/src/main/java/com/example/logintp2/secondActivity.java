package com.example.logintp2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
public class secondActivity extends AppCompatActivity {
    public EditText Monnaie;
    public RadioButton conv1, conv2;
    public Button conv;
    public TextView res;
    public Button btn_deconnexion;

    @SuppressLint("SetTextI18n")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Monnaie = findViewById(R.id.Monnaie);
        conv1 = findViewById(R.id.conv1);
        conv2 = findViewById(R.id.conv2);
        conv = findViewById(R.id.conv);
        res = findViewById(R.id.res);
        btn_deconnexion = findViewById(R.id.btn_deconnexion);
        btn_deconnexion.setOnClickListener(v -> {
            Intent intent = new Intent(secondActivity.this, MainActivity.class);
            startActivity(intent);
        });
        conv.setOnClickListener(v -> {
            if (Monnaie.getText().toString().isEmpty()){
                AlertDialog alertDialog;
                alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Missing Fields!!");
                alertDialog.setMessage("Insert an number to convert it !!!");
                alertDialog.show();
            } else {
                double resultat = Double.parseDouble(String.valueOf(Monnaie.getText()));
                if(conv1.isChecked()){
                    resultat = resultat * 3.1;
                    res.setText("Result : "+resultat);
                } else if (conv2.isChecked()){
                    resultat = resultat * 0.3;
                    res.setText("Result : "+resultat);
                }else {
                    AlertDialog alertDialog;
                    alertDialog = new AlertDialog.Builder(this).create();
                    alertDialog.setTitle("Invalid!!");
                    alertDialog.setMessage("Check Buttom !!!");
                    alertDialog.show();
                }
            }
    });
        conv1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.showContextMenu();
                return true;
            }
        });
        conv1.setOnCreateContextMenuListener(this);
        conv2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.showContextMenu();
                return true;
            }
        });
        conv2.setOnCreateContextMenuListener(this);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, 0, "EURO ---> Dinar");
        menu.add(0, 2, 0, "Dinar ---> EURO");
        menu.add(0, 3, 0, "C <--> F");
        menu.add(0, 4, 0, "F <--> C");
        menu.add(0, 5, 0, "Log Out");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                // Conversion EURO -> Dinar
                Toast.makeText(this, "3,1", Toast.LENGTH_LONG).show();
                break;
            case 2:
                // Conversion Dinar -> EURO
                Toast.makeText(this, "0,3", Toast.LENGTH_LONG).show();
                break;
            case 3:
                // Conversion C <-> F
                Intent i = new Intent(this, Main3Activity.class);
                startActivity(i);
                break;
            case 4:
                // Conversion F <-> C
                Intent j = new Intent(this, Main3Activity.class);
                startActivity(j);
                break;
            case 5:
                // Sortie de l'application
                this.finish();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Temperature Conversion");
        menu.add(0, 2, 0, "Log Out");
        return super.onCreateOptionsMenu(menu);
    }
    // Méthode appelée lorsque l'utilisateur sélectionne une option du menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent i = new Intent(secondActivity.this,Main3Activity.class);
                startActivity(i);
                break;
            case 2:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
