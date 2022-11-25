package com.example.gestorincidencies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.*;
import java.util.*;

public class LlistatIncidencies extends AppCompatActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.llistat);
        btn = findViewById(R.id.home);

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
        List<Map<String,String>> data = null;
        data = new ArrayList<Map<String,String>>();

        try {
            ConnexioBD connectionHelper = new ConnexioBD();
            connection = connectionHelper.connect();
            if (connection != null) {

                String query = "Select * from incidencies2";

                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);

                while (rs.next()){
                    HashMap<String, String> hm = new HashMap<String, String>();
                    hm.put("id", rs.getString("id"));
                    hm.put("usuari", rs.getString("usuari"));
                    hm.put("tipus", rs.getString("tipus"));
                    hm.put("marca", rs.getString("marca"));
                    hm.put("ubicacio", rs.getString("ubicacio"));
                    hm.put("descripcio", rs.getString("descripcio"));
                    hm.put("data", rs.getString("data"));

                     data.add(hm);
                }
            } else {
                ConnectionResult = "Check Connection";
                Toast.makeText(this, "dins del else", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {
            Log.e("Error: ", ex.getMessage());
        }

        String[] from = {"id", "usuari", "tipus", "marca", "ubicacio", "descripcio", "data"};
        int[] to = {R.id.id, R.id.usuari, R.id.tipus, R.id.marca, R.id.ubicacio, R.id.descripcio, R.id.data};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), data, R.layout.llistat_item, from, to);
        ListView androidListView = (ListView) findViewById(R.id.lv);
        androidListView.setAdapter(simpleAdapter);

    }
}