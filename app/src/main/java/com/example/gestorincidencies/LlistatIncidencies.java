package com.example.gestorincidencies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
                    Map<String,String> dtname = new HashMap<String, String>();

                    dtname.put("id",rs.getString("id"));

                    Toast.makeText(this, rs.getString("id"), Toast.LENGTH_SHORT).show();

                    data.add(dtname);
                }
            } else {
                ConnectionResult = "Check Connection";
                Toast.makeText(this, "dins del else", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {
            Log.e("Error: ", ex.getMessage());
        }

    }
}