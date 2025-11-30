package com.example.projetm2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class choose extends AppCompatActivity {

    Button btn_algo, btn_c, btnLogout;
    TextView tvUsername;

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        session = new SessionManager(this);

        // If user is not logged in → redirect to login
        if (!session.isLoggedIn()) {
            startActivity(new Intent(this, log_in.class));
            finish();
            return;
        }

        // UI elements
        tvUsername = findViewById(R.id.tvUsername);
        btnLogout = findViewById(R.id.btnLogout);
        btn_algo = findViewById(R.id.btnAlgorithmic);
        btn_c = findViewById(R.id.btnC_Language);

        // Display the real username
        tvUsername.setText(session.getUsername());

        // Logout button
        btnLogout.setOnClickListener(v -> {
            session.logout();
            Intent intent = new Intent(choose.this, log_in.class);
            startActivity(intent);
            finish();
        });

        // Navigate to course selections
        btn_algo.setOnClickListener(v -> {
            Intent intent = new Intent(choose.this, what_to_do.class);
            startActivity(intent);
        });

        btn_c.setOnClickListener(v -> {
            Intent intent = new Intent(choose.this, what_to_do_C.class);
            startActivity(intent);
        });
    }
}
