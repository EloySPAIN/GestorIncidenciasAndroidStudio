package com.example.gestorincidencies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LlistatIncidencies extends AppCompatActivity {
    private Button btMostrar;
    private Button btn;
    private TextView tvResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.llistat);
        btn = findViewById(R.id.homeListat);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LlistatIncidencies.this,MainActivity.class));
            }
        });
    }

    Connection connection;
    String ConnectionResult="";

    public void getTextFromSQL(View v){
        btMostrar = findViewById(R.id.mostrar);
        tvResultat = findViewById(R.id.tvidResultat);

        try{
            ConnexioBD connectionHelper = new ConnexioBD();
            connection = connectionHelper.connect();
            if(connection!=null){
                String query = "Select * from incidencies";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);

                while(rs.next()){
                    tvResultat.setText(rs.getString());
                }
            }
        }catch (Exception ex){
            Log.e("Error: ", ex.getMessage());
        }
    }
