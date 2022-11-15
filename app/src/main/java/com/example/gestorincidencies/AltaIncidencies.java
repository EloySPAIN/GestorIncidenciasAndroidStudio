package com.example.gestorincidencies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AltaIncidencies extends AppCompatActivity {
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta);

        btn = findViewById(R.id.homeAlta);

        btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
                startActivity(new Intent(AltaIncidencies.this,MainActivity.class));
          }
      });

    }

}
