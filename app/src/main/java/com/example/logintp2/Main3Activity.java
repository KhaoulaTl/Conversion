package com.example.logintp2;
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

public class Main3Activity extends AppCompatActivity {

    RadioButton conv1, conv2;
    EditText temperature;
    TextView res;
    Button btn_deconnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        temperature = (EditText) findViewById(R.id.temperature);
        res = (TextView) findViewById(R.id.res);
        conv1 = (RadioButton) findViewById(R.id.conv1);
        conv2 = (RadioButton) findViewById(R.id.conv2);
        Button conv = (Button) findViewById(R.id.conv);
        btn_deconnexion = findViewById(R.id.btn_deconnexion);
        btn_deconnexion.setOnClickListener(v -> {
            Intent intent = new Intent(Main3Activity.this, MainActivity.class);
            startActivity(intent);
        });
        conv.setOnClickListener(v -> {
            if (temperature.getText().toString().isEmpty()){
                AlertDialog alertDialog;
                alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Missing Fields!!");
                alertDialog.setMessage("Insert an number to convert it !!!");
                alertDialog.show();
            } else {
                double resultat = Double.parseDouble(String.valueOf(temperature.getText()));
                if(conv1.isChecked()){
                    resultat = resultat * 9 / 5 + 32;
                    res.setText("Result : "+resultat);
                } else if (conv2.isChecked()){
                    resultat = resultat * - 32 * 5 / 9;
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

        btn_deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private float CelciusToFarenheit(float valeurCelsius) {
        return (float) (valeurCelsius * 9 / 5 + 32);
    }

    private float FarenheitToCelcius(float valeurFahrenheit) {
        return (float) ((valeurFahrenheit - 32) * 5 / 9);
    }

    public void convertir(View v) {
        String input = temperature.getText().toString();
        float valeurInitiale;
        try {
            valeurInitiale = Float.valueOf(input);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Enter a valid number!.", Toast.LENGTH_SHORT).show();
            return;
        }

        float resultat;

        if (conv1.isChecked()) {
            resultat = CelciusToFarenheit(valeurInitiale);

        } else {
            resultat = FarenheitToCelcius(valeurInitiale);
        }
        res.setText(String.valueOf(resultat));
    }
    // Méthode appelée pour créer le menu contextuel
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        // Ajout des options de conversion et de sortie du programme
        menu.add(0, 1, 0, "C <--> F");
        menu.add(0, 2, 0, "F <--> C" );
        menu.add(0, 3, 0, "EURO ---> Dinar");
        menu.add(0, 4, 0, "Dinar ---> EURO");
        menu.add(0, 5, 0, "Log Out");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1:
                // Conversion C <-> F
                Toast.makeText(this,"C To F",Toast.LENGTH_LONG).show();
                break;
            case 2:
                // Conversion F <-> C
                Toast.makeText(this,"F To C",Toast.LENGTH_LONG).show();
                break;
            case 3:
                // Conversion EURO -> Dinar
                Intent i = new Intent(this,secondActivity.class);
                startActivity(i);
                break;
            case 4:
                // Conversion Dinar -> EURO
                Intent j = new Intent(this,secondActivity.class);
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
        getMenuInflater().inflate(R.menu.menu_conversion2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_conversion:
                // lancer l'activité de conversion de monnaie
                Intent intentTemperature = new Intent(this, secondActivity.class);
                startActivity(intentTemperature);
                return true;
            case R.id.menu_item_quitter:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void convert(View v){
        if (temperature.getText().toString().equals("")){
            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Missing Fields!!");
            alertDialog.setMessage("Insert an number to convert it !!!");
            alertDialog.show();
        } else {
            if (temperature.getText().toString().equals(".")){
                AlertDialog alertDialog;
                alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Invalid Fields!!");
                alertDialog.setMessage("Insert an number to convert it !!!");
                alertDialog.show();}
        }
    }
}
