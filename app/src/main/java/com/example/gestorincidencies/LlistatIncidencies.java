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

    private Button btn;
    Connection connect;
    private String id;
    private EditText idAMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.llistat);
        btn = findViewById(R.id.home);
        idAMostrar = findViewById(R.id.idAMostrar);
        id = idAMostrar.getText().toString().trim();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LlistatIncidencies.this, MainActivity.class));
            }
        });
    }

    Connection connection;
    String ConnectionResult = "";

    public void getTextFromSQL(View v) {
        TextView user = (TextView) findViewById(R.id.tvUser);
        TextView tipus = (TextView) findViewById(R.id.tvTipus);
        TextView marca = (TextView) findViewById(R.id.tvMarca);
        TextView ubcacio = (TextView) findViewById(R.id.tvUbicacio);
        TextView descripcio = (TextView) findViewById(R.id.tvDescripcio);
        TextView data = (TextView) findViewById(R.id.tvData);

        try{
            ConnexioBD connexioBD = new ConnexioBD();
            connect = connexioBD.connect();

            if (connect != null) {
                String query = "select * from incidencies2 where id='" + id + "';";
                Statement smt = connect.createStatement();
                ResultSet rs = smt.executeQuery(query);

                while(rs.next()){
                    user.setText(rs.getString(2));
                    user.setText(rs.getString(3));
                    user.setText(rs.getString(4));
                    user.setText(rs.getString(5));
                    user.setText(rs.getString(6));
                }
            }

        }catch (Exception ex){
            Log.e("Error: ", ex.getMessage());
        }
    }
}