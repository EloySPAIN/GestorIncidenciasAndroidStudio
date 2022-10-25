package com.example.gestorincidencies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {

    private Button btAlta, btLlistat, btResolucio, btHomeAlta, btHomeLlistat, btHomeResolucio;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btAlta = findViewById(R.id.b1);
        btLlistat = findViewById(R.id.b2);
        btResolucio = findViewById(R.id.b3);
        btHomeAlta = findViewById(R.id.homeAlta);
        btHomeLlistat = findViewById(R.id.homeListat);
        btHomeResolucio = findViewById(R.id.homeResolucio);
    }

    public void changeLayout(View v){

        if(v==btAlta){
            setContentView(R.layout.alta);
        }else if(v==btLlistat){
            setContentView(R.layout.llistat);
        }else if(v==btResolucio){
            setContentView(R.layout.resolucio);
        }

    }


}