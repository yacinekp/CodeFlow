package com.example.projetm2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {

    private TextView tvEmail;
    private EditText etNewPassword, etConfirmPassword;
    private Button btnResetPassword, btnBackToLogin;
    private String username, email;
    private database_Handler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        db = new database_Handler(this);

        // Get username and email from intent
        username = getIntent().getStringExtra("username");
        email = getIntent().getStringExtra("email");

        if (username == null || email == null) {
            Toast.makeText(this, "Error: Invalid reset request", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        tvEmail = findViewById(R.id.tv_email);
        etNewPassword = findViewById(R.id.et_new_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btnResetPassword = findViewById(R.id.btn_reset_password);
        btnBackToLogin = findViewById(R.id.btn_back_to_login);

        // Show user email
        tvEmail.setText("Resetting password for: " + email);

        // REMOVED THESE LINES - They were trying to hide non-existent elements
        // findViewById(R.id.et_reset_code).setVisibility(View.GONE);
        // findViewById(R.id.btn_verify_code).setVisibility(View.GONE);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = etNewPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(ResetPasswordActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!newPassword.equals(confirmPassword)) {
                    Toast.makeText(ResetPasswordActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validate password
                String passwordError = validatePassword(newPassword);
                if (passwordError != null) {
                    Toast.makeText(ResetPasswordActivity.this, passwordError, Toast.LENGTH_LONG).show();
                    return;
                }

                // Update password in database
                String hashed = String.valueOf(newPassword.hashCode());
                boolean success = db.updatePassword(email, hashed);

                if (success) {
                    Toast.makeText(ResetPasswordActivity.this, "Password reset successfully!", Toast.LENGTH_SHORT).show();

                    // Go to login
                    Intent intent = new Intent(ResetPasswordActivity.this, log_in.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(ResetPasswordActivity.this, "Failed to reset password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPasswordActivity.this, log_in.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private String validatePassword(String password) {
        if (password.length() < 8) {
            return "Password must be at least 8 characters long";
        }

        if (!password.matches(".*[A-Z].*")) {
            return "Password must contain at least one uppercase letter";
        }

        if (!password.matches(".*[0-9].*")) {
            return "Password must contain at least one number";
        }

        if (!password.matches(".*[@#$%^&+=!].*")) {
            return "Password must contain at least one special character (@, #, $, %, etc.)";
        }

        return null;
    }
}