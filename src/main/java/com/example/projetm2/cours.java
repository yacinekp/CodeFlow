package com.example.projetm2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class cours extends AppCompatActivity {

    Button crprv, btnLogout;
    TextView cour, tvUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cours);

        // SESSION UI
        tvUsername = findViewById(R.id.tvUsername);
        btnLogout = findViewById(R.id.btnLogout);

        // CONTENT UI
        crprv = findViewById(R.id.btncrprevious);
        cour = findViewById(R.id.CourseContent);

        // Load session
        SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
        String username = prefs.getString("username", null);

        if (username == null) {
            // No session → return to login
            Intent intent = new Intent(cours.this, log_in.class);
            startActivity(intent);
            finish();
            return;
        }

        tvUsername.setText(username);

        // Logout
        btnLogout.setOnClickListener(v -> {
            prefs.edit().clear().apply();
            Intent intent = new Intent(cours.this, log_in.class);
            startActivity(intent);
            finish();
        });

        // Load content from intent
        String coursTxt = getIntent().getStringExtra("cours");
        if (coursTxt != null) {
            cour.setText(Html.fromHtml(coursTxt, Html.FROM_HTML_MODE_LEGACY));
        }

        // Previous button
        crprv.setOnClickListener(v -> {
            Intent intent = new Intent(cours.this, what_to_do.class);
            startActivity(intent);
        });
    }
}
