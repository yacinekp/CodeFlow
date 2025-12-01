package com.example.projetm2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class log_in extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin, btnCreate;
    Button btnForgot;

    database_Handler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        db = new database_Handler(this);

        etUsername = findViewById(R.id.idusername);
        etPassword = findViewById(R.id.idpassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnCreate = findViewById(R.id.btnCreateAccount);
        btnForgot = findViewById(R.id.btnForgotPassword);

        btnForgot.setOnClickListener(v -> {
            Intent i = new Intent(log_in.this, forgot_password.class);
            startActivity(i);
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etUsername.getText().toString().trim();
                String pass = etPassword.getText().toString();

                if (username.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(log_in.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                String hashed = String.valueOf(pass.hashCode());

                boolean success = db.loginUser(username, hashed);

                if (success) {
                    db.updateLastActive(username);
                    Toast.makeText(log_in.this, "Login successful", Toast.LENGTH_SHORT).show();

                    // CREATE SESSION
                    SessionManager session = new SessionManager(log_in.this);
                    session.createSession(username);

                    Intent serviceIntent = new Intent(log_in.this, timetrackservice.class);
                    serviceIntent.putExtra("username", username);
                    startService(serviceIntent);

                    Intent i = new Intent(log_in.this, choose.class);
                    startActivity(i);
                } else {
                    Toast.makeText(log_in.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCreate.setOnClickListener(v -> {
            Intent i = new Intent(log_in.this, creat_account.class);
            startActivity(i);
        });
    }
}
