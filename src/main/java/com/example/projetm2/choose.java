package com.example.projetm2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class choose extends AppCompatActivity {

    Button btn_algo,btn_c;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        btn_algo = findViewById(R.id.btnAlgorithmic);

        btn_c =findViewById(R.id.btnC_Language);

        btn_algo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(choose.this, what_to_do.class);
                startActivity(intent);
            }
        });
        btn_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(choose.this, what_to_do_C.class);
                startActivity(intent);
            }
        });
    }
}