package com.example.gestorincidencies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.*;
import java.util.*;

public class LlistatIncidencies extends AppCompatActivity {

    //Aqui declarem les variables que fem servir
    private Button btn;
    Connection connect;
    private String id;
    private EditText idAMostrar;

    //creem el metode onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.llistat);

        //declarem les variables locals del onCreate
        btn = findViewById(R.id.home);
        idAMostrar = findViewById(R.id.idAMostrar);
        id = idAMostrar.getText().toString().trim();

        btn.setOnClickListener(new View.OnClickListener() {

            //declarem la funcio onClick dins del onCreate aquesta funció la tindrà el botó de homr
            @Override
            public void onClick(View v) {
                //Cridem al main activity
                startActivity(new Intent(LlistatIncidencies.this, MainActivity.class));
            }
        });
    }

    //declarem les variables necessaries per a la connexió a la BBDD
    Connection connection;
    String ConnectionResult = "";

    //declarem el mètode que farà el select de la BBDD
    public void getTextFromSQL(View v) {
        //devlarem les variables linkantles amb la part visual per a poder tractar-les
        TextView user = (TextView) findViewById(R.id.tvUser);
        TextView tipus = (TextView) findViewById(R.id.tvTipus);
        TextView marca = (TextView) findViewById(R.id.tvMarca);
        TextView ubcacio = (TextView) findViewById(R.id.tvUbicacio);
        TextView descripcio = (TextView) findViewById(R.id.tvDescripcio);
        TextView data = (TextView) findViewById(R.id.tvData);

        //farem un try catch per a connectarnos a la BBDD i si falla ens mostrarà l'error
        try{
            //cridem a la funcio de connectar-nos a la BBDD del ConnexióBD
            ConnexioBD connexioBD = new ConnexioBD();
            connect = connexioBD.connect();

            //si la connexió no es null farem el select de la BBDD
            if (connect != null) {
                String query = "select * from incidencies2 where id='" + id + "';";
                Statement smt = connect.createStatement();
                ResultSet rs = smt.executeQuery(query);

                //mentres la query vagi tenint lnies les asignarem a les variables de la part frontal
                while(rs.next()){
                    user.setText(rs.getString(2));
                    tipus.setText(rs.getString(3));
                    marca.setText(rs.getString(4));
                    ubcacio.setText(rs.getString(5));
                    descripcio.setText(rs.getString(6));
                    data.setText(rs.getString(7));
                }
            }

        }catch (Exception ex){
            //mostrarà l'error en cas de que no es pugui connectar a la BBDD
            Log.e("Error: ", ex.getMessage());
        }
    }
}