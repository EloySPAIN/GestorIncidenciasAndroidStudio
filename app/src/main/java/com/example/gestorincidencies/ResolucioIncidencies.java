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
import java.util.HashMap;

public class ResolucioIncidencies extends AppCompatActivity {
    private Button btn, btnSubmit;

    private TextView eNom, eTipus, eMarca, eUbi, eDesc, eData,eResolt;
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
        eTipus = findViewById(R.id.tTipus);
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

                            eNom.setText(hm.get("usuari"));
                            eTipus.setText(hm.get("tipus"));
                            eMarca.setText(hm.get("marca"));
                            eUbi.setText(hm.get("ubicacio"));
                            eDesc.setText(hm.get("descripcio"));
                            eData.setText(hm.get("data"));
                            eResolt.setText("Resolta!");
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
