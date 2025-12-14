package com.example.projetm2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class forgot_password extends AppCompatActivity {

    EditText etEmail;
    Button btnReset, btnBackToLogin;
    TextView tvMessage;

    database_Handler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        db = new database_Handler(this);

        etEmail = findViewById(R.id.etUsername); // Same ID but we'll use for email
        btnReset = findViewById(R.id.btnReset);
        btnBackToLogin = findViewById(R.id.btnBackToLogin);
        tvMessage = findViewById(R.id.tvMessage);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();

                if (email.isEmpty()) {
                    Toast.makeText(forgot_password.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validate email format
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    tvMessage.setText("Please enter a valid email address");
                    tvMessage.setTextColor(ContextCompat.getColor(forgot_password.this, android.R.color.holo_red_dark));
                    return;
                }

                // Check if email exists in database
                if (!db.emailExists(email)) {
                    tvMessage.setText("Email not found. Please check your email.");
                    tvMessage.setTextColor(ContextCompat.getColor(forgot_password.this, android.R.color.holo_red_dark));
                    return;
                }

                // Email is valid and exists - go to reset screen
                tvMessage.setText("Email verified! Redirecting to password reset...");
                tvMessage.setTextColor(ContextCompat.getColor(forgot_password.this, android.R.color.holo_green_dark));

                // Get username from email
                String username = db.getUsernameByEmail(email);

                // Go to reset password screen
                Intent intent = new Intent(forgot_password.this, ResetPasswordActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("email", email);
                startActivity(intent);
                finish();
            }
        });

        btnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(forgot_password.this, log_in.class);
                startActivity(intent);
                finish();
            }
        });
    }
}