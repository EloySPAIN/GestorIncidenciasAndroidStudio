package com.example.gestorincidencies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AltaIncidencies extends AppCompatActivity {
    private Button btn;
    private Spinner spinnerMarca;
    private ArrayAdapter<CharSequence>adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta);

        btn = findViewById(R.id.homeAlta);
        spinnerMarca = findViewById(R.id.spinnerTipus);
        adaptador=ArrayAdapter.createFromResource(this, R.array.tipusMarca, android.R.layout.simple_spinner_item);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerMarca.setAdapter(adaptador);

        btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
                startActivity(new Intent(AltaIncidencies.this,MainActivity.class));
          }
      });

    }

}
