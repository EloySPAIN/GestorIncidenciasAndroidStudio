package com.example.gestorincidencies;

import android.Manifest;
import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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
    private NotificationUtils mNotificationUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Pregunta a l'usuari l'acces de rebre i enviar notificacions al telefon
        if(ActivityCompat.checkSelfPermission(
                AltaIncidencies.this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                AltaIncidencies.this,Manifest
                        .permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(AltaIncidencies.this,new String[]
                    { Manifest.permission.SEND_SMS,},1);
        }else{
        };
        
        mNotificationUtils = new NotificationUtils(this);

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
                    boolean esVacio = false;
                    String vacio = "";
                    nom = eNom.getText().toString().trim();
                    tipo = spinnerMarca.getSelectedItem().toString();
                    marca = eMarca.getText().toString().trim();
                    ubi = eUbi.getText().toString().trim();
                    desc = eDesc.getText().toString().trim();
                    data = eData.getText().toString().replaceAll("/", "-");

                    ConnexioBD connectionHelper = new ConnexioBD();
                    connection = connectionHelper.connect();

                    //Control d'errors per no enviar camps buits
                    if (connection != null) {

                        if(nom.isEmpty()){
                            vacio = vacio + "'Nom Usuari' ";
                            esVacio = true;
                        }
                        if (tipo.isEmpty()){
                            vacio = vacio + "'Tipus' ";
                            esVacio = true;
                        }
                        if (marca.isEmpty()){
                            vacio = vacio + "'Marca' ";
                            esVacio = true;
                        }
                        if (ubi.isEmpty()){
                            vacio = vacio + "'Ubicaci??' ";
                            esVacio = true;
                        }
                        if (desc.isEmpty()){
                            vacio = vacio + "'Descripci??' ";
                            esVacio = true;
                        }
                        if (data.isEmpty()){
                            vacio = vacio + "'Data' ";
                            esVacio = true;
                        }
                        //Comprovar si hi estan buits o no per enviar al sql les dades
                        if(esVacio){
                            String comVacio = "Els camps " + vacio + "no estan omplerts";
                            SpannableStringBuilder biggerText = new SpannableStringBuilder(comVacio);
                            biggerText.setSpan(new RelativeSizeSpan(0.90f), 0, comVacio.length(), 0);
                            Toast.makeText(AltaIncidencies.this, biggerText, Toast.LENGTH_SHORT).show();
                            esVacio = false;
                        }else{
                            String query = "INSERT INTO incidencies2 (usuari,tipus,marca,ubicacio,descripcio,data, resolta) VALUES ('"+nom+"','"+tipo+"','"+marca+"','"+ubi+"','"+desc+"', str_to_date('"+data+"', '%d-%m-%Y'), 0);";
                            Statement st = connection.createStatement();
                            st.executeUpdate(query);

                            //Construim la notificacio que volem que s'envii a l'usuari
                            Notification.Builder nb = mNotificationUtils.
                                    getAndroidChannelNotification("Gestor d'incidencies", "La incidencia s'ha donat d'alta correctament");

                            mNotificationUtils.getManager().notify(101, nb.build());

                            Toast.makeText(AltaIncidencies.this, "Datos enviados", Toast.LENGTH_SHORT).show();
                            connection.close();
                        }

                        connection.close();
                    } else {
                        Toast.makeText(AltaIncidencies.this, "error", Toast.LENGTH_SHORT).show();
                    }
                    connection.close();
                } catch (Exception ex) {
                    Log.e("Error: ", ex.getMessage());
                }
            }
        });

    }

}
