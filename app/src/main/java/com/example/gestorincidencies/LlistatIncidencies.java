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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.*;
import java.util.*;

public class LlistatIncidencies extends AppCompatActivity {

    private Button btn;
    private String id;
    private EditText idAMostrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.llistat);
        btn = findViewById(R.id.home);
        idAMostrar = findViewById(R.id.idAMostrar);
        id = idAMostrar.getText().toString();

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
        List<Map<String,String>> data2 = null;
        data2 = new ArrayList<Map<String,String>>();
        TextView user = (TextView) findViewById(R.id.tvUser);
        TextView tipus = (TextView) findViewById(R.id.tvTipus);
        TextView marca = (TextView) findViewById(R.id.tvMarca);
        TextView ubicacio = (TextView) findViewById(R.id.tvUbicacio);
        TextView descripcio = (TextView) findViewById(R.id.tvDescripcio);
        TextView data = (TextView) findViewById(R.id.tvData);
        try {
            ConnexioBD connectionHelper = new ConnexioBD();
            connection = connectionHelper.connect();
            if (connection != null) {
                HashMap<String, String> hm = new HashMap<String, String>();
                String query = "select * from incidencies2 where id = 1";

                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);


                while (rs.next()){

                    hm.put("id", rs.getString("id"));
                    hm.put("usuari", rs.getString("usuari"));
                    hm.put("tipus", rs.getString("tipus"));
                    hm.put("marca", rs.getString("marca"));
                    hm.put("ubicacio", rs.getString("ubicacio"));
                    hm.put("descripcio", rs.getString("descripcio"));
                    hm.put("data", rs.getString("data"));
                    hm.put("resolta", rs.getString("resolta"));

                    user.setText(hm.get("usuari"));
                    tipus.setText(hm.get("tipus"));
                    marca.setText(hm.get("marca"));
                    ubicacio.setText(hm.get("ubicacio"));
                    descripcio.setText(hm.get("descripcio"));
                    data.setText(hm.get("data"));
                }


            } else {
                ConnectionResult = "Failed";
            }
        } catch (Exception ex) {
            Log.e("Error: ", ex.getMessage());
        }



    }
}