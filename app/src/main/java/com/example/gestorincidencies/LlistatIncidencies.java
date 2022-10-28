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
    private TextView tvIdResultat, tvNomResultat, tvCognomResultat, tvResumResultat, tvTelefResultat, tvDescResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.llistat);
        btn = findViewById(R.id.homeListat);

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
        btMostrar = findViewById(R.id.mostrar);
        tvIdResultat = findViewById(R.id.tvidResultat);
        tvNomResultat = findViewById(R.id.tvnomResultat);
        tvCognomResultat = findViewById(R.id.tvcognomResultat);
        tvResumResultat = findViewById(R.id.tvresumResultat);
        tvTelefResultat = findViewById(R.id.tvtelefonResultat);
        tvDescResultat = findViewById(R.id.tvdescripcioResultat);

        try {
            ConnexioBD connectionHelper = new ConnexioBD();
            connection = connectionHelper.connect();
            if (connection != null) {
                String query = "Select * from incidencies";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {
                    tvIdResultat.setText(rs.getString(1));
                    tvNomResultat.setText(rs.getString(2));
                    tvCognomResultat.setText(rs.getString(3));
                    tvResumResultat.setText(rs.getString(4));
                    tvTelefResultat.setText(rs.getString(6));
                    tvDescResultat.setText(rs.getString(5));
                }
            }else{
                ConnectionResult="Check Connection";
            }
        } catch (Exception ex) {
            Log.e("Error: ", ex.getMessage());
        }
    }
}