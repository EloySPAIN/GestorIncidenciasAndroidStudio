package com.example.gestorincidencies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {

    private Button btAlta, btLlistat, btResolucio;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Agafem els botons del xml hi ho afegim a les variables
        btAlta = findViewById(R.id.b1);
        btLlistat = findViewById(R.id.b2);
        btResolucio = findViewById(R.id.b3);
    }
    
    // Permet canviar entre els diferents layouts de l'aplicació
    public void changeLayout(View v){

        if(v==btAlta){
            startActivity(new Intent(this,AltaIncidencies.class));
        }else if(v==btLlistat){
            startActivity(new Intent(this,LlistatIncidencies.class));
        }else if(v==btResolucio){
            startActivity(new Intent(this,ResolucioIncidencies.class));
        }
    }
}
