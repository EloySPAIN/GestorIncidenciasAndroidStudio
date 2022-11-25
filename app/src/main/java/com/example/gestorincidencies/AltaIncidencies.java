package com.example.gestorincidencies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AltaIncidencies extends AppCompatActivity {
    private Button btnHome, btnSubmit;
    private Spinner spinnerMarca;
    private ArrayAdapter<CharSequence> adaptador;
    Connection connection;
    private EditText eNom, eMarca, eUbi, eDesc, eData;
    private String nom, tipo, marca, ubi, desc, data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta);

        btnHome = findViewById(R.id.homeAlta);

        eNom = findViewById(R.id.editTextNom);
        eMarca = findViewById(R.id.editTextMarca);
        eUbi = findViewById(R.id.editTextUbi);
        eDesc = findViewById(R.id.editTextDesc);
        eData = findViewById(R.id.editTextData);

        spinnerMarca = findViewById(R.id.spinnerTipus);
        adaptador = ArrayAdapter.createFromResource(this, R.array.tipusMarca, android.R.layout.simple_spinner_item);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerMarca.setAdapter(adaptador);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AltaIncidencies.this, MainActivity.class));
            }
        });


        btnSubmit = findViewById(R.id.submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    nom = eNom.getText().toString().trim();
                    tipo = spinnerMarca.getSelectedItem().toString();
                    marca = eMarca.getText().toString().trim();
                    ubi = eUbi.getText().toString().trim();
                    desc = eDesc.getText().toString().trim();
                    data = eData.getText().toString().replaceAll("/", "-");

                    ConnexioBD connectionHelper = new ConnexioBD();
                    connection = connectionHelper.connect();
                    if (connection != null) {

                        String query = "INSERT INTO incidencies2 (usuari,tipus,marca,ubicacio,descripcio,data) VALUES ('"+nom+"','"+tipo+"','"+marca+"','"+ubi+"','"+desc+"', str_to_date('"+data+"', '%d-%m-%Y'));";
                        Statement st = connection.createStatement();
                        st.executeUpdate(query);

                        Toast.makeText(AltaIncidencies.this, "Datos enviados", Toast.LENGTH_SHORT).show();

                        connection.close();
                    } else {
                        Toast.makeText(AltaIncidencies.this, "error", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    Log.e("Error: ", ex.getMessage());
                }
            }
        });

    }

}
