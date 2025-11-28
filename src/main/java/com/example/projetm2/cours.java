package com.example.projetm2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class cours extends AppCompatActivity {
    Button crprv;
    TextView cour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cours);
        crprv = findViewById(R.id.btncrprevious);
        cour = findViewById(R.id.CourseContent);
        String cours = getIntent().getStringExtra("cours");
        if (cours != null) {
            cour.setText(Html.fromHtml(cours, Html.FROM_HTML_MODE_LEGACY));
        }

        crprv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cours.this, what_to_do.class);
                startActivity(intent);
            }
        });
    }
}