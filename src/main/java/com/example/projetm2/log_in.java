package com.example.projetm2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class log_in extends AppCompatActivity {

    EditText etUsernameOrEmail, etPassword;
    Button btnLogin, btnCreate, btnForgotPassword, btnCreateNewAccount, btnResetPassword;
    LinearLayout regularOptionsLayout, errorOptionsLayout;
    TextView tvErrorMessage;

    database_Handler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        db = new database_Handler(this);

        // Initialize UI elements
        etUsernameOrEmail = findViewById(R.id.idusername);
        etPassword = findViewById(R.id.idpassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Layouts
        regularOptionsLayout = findViewById(R.id.regularOptionsLayout);
        errorOptionsLayout = findViewById(R.id.errorOptionsLayout);
        tvErrorMessage = findViewById(R.id.tvErrorMessage);

        // Regular options buttons
        btnCreate = findViewById(R.id.btnCreateAccount);
        btnForgotPassword = findViewById(R.id.btnForgotPassword);

        // Error options buttons
        btnCreateNewAccount = findViewById(R.id.btnCreateNewAccount);
        btnResetPassword = findViewById(R.id.btnResetPassword);

        // Login button - Accepts username OR email
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameOrEmail = etUsernameOrEmail.getText().toString().trim();
                String pass = etPassword.getText().toString();

                if (usernameOrEmail.isEmpty() || pass.isEmpty()) {
                    showErrorOptions("Please fill in all fields");
                    return;
                }

                String hashed = String.valueOf(pass.hashCode());
                boolean success = db.loginUser(usernameOrEmail, hashed);

                if (success) {
                    // Hide error options on successful login
                    showRegularOptions();

                    // Get the actual username for session
                    String[] userInfo = db.getUserInfo(usernameOrEmail);
                    String actualUsername = userInfo != null ? userInfo[0] : usernameOrEmail;

                    db.updateLastActive(actualUsername);
                    Toast.makeText(log_in.this, "Login successful", Toast.LENGTH_SHORT).show();

                    SessionManager session = new SessionManager(log_in.this);
                    session.createSession(actualUsername);

                    Intent i = new Intent(log_in.this, choose.class);
                    startActivity(i);
                    finish();
                } else {
                    showErrorOptions("Wrong username/email or password");
                }
            }
        });

        // Regular Create account button
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(log_in.this, creat_account.class);
                startActivity(i);
            }
        });

        // Regular Forgot password button
        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(log_in.this, forgot_password.class);
                startActivity(intent);
            }
        });

        // Error Create New Account button
        btnCreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(log_in.this, creat_account.class);
                startActivity(i);
            }
        });

        // Error Reset Password button
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(log_in.this, forgot_password.class);
                startActivity(intent);
            }
        });
    }

    private void showErrorOptions(String errorMessage) {
        tvErrorMessage.setText(errorMessage);
        regularOptionsLayout.setVisibility(View.GONE);
        errorOptionsLayout.setVisibility(View.VISIBLE);
    }

    private void showRegularOptions() {
        regularOptionsLayout.setVisibility(View.VISIBLE);
        errorOptionsLayout.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Clear fields and show regular options when returning
        etUsernameOrEmail.setText("");
        etPassword.setText("");
        showRegularOptions();
    }
}