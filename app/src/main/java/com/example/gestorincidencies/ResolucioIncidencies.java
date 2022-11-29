package com.example.gestorincidencies;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ResolucioIncidencies extends AppCompatActivity {
    private Button btn, btnSubmit;

    private TextView eNom, eMarca, eUbi, eDesc, eData,eResolt;
    private String nom, tipo, marca, ubi, desc, data, id;
    private EditText eid;
    Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resolucio);
        
        //Variables que agafen el TextView
        eid = findViewById(R.id.id);
        id= eid.getText().toString().trim();
        eNom = findViewById(R.id.tUsuari);
        eMarca = findViewById(R.id.tMarca);
        eUbi = findViewById(R.id.tUbi);
        eDesc = findViewById(R.id.tDesc);
        eData = findViewById(R.id.tData);
        eResolt = findViewById(R.id.tEstat);

        btn = findViewById(R.id.homeResolucio);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResolucioIncidencies.this,MainActivity.class));
            }
        });
        
        //Mostrar informacó de cada incidencia
        btnSubmit = findViewById(R.id.busca);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //Conexió a la base de dades
                    ConnexioBD connectionHelper = new ConnexioBD();
                    connection = connectionHelper.connect();
                    //Comprova si hi ha conexió
                    if (connection != null) {
                        String query = "select * from incidencies2 where id='" + id + "';";
                        Statement smt = connection.createStatement();
                        ResultSet rs = smt.executeQuery("select * from incidencies2 where id='" + id + "';");
                        while(rs.next()){
                            Toast.makeText(ResolucioIncidencies.this, rs.getString(1), Toast.LENGTH_SHORT).show();
                        }
                    }
                    connection.close();
                } catch (Exception ex) {
                    Log.e("Error: ", ex.getMessage());
                }
            }
        });

    }
}
