package com.example.projetm2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class creat_account extends AppCompatActivity {

    EditText etUsername, etPassword, etConfirmPassword;
    Button create;

    database_Handler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_account);

        db = new database_Handler(this);

        etUsername = findViewById(R.id.CreateAccountUserName);
        etPassword = findViewById(R.id.btnCreateAccountPassword);
        etConfirmPassword = findViewById(R.id.btnCreateAccountConfirmPassword);

        create = findViewById(R.id.btnCreate);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString();
                String confirm = etConfirmPassword.getText().toString();

                // Validations
                if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                    Toast.makeText(creat_account.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirm)) {
                    Toast.makeText(creat_account.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (db.usernameExists(username)) {
                    Toast.makeText(creat_account.this, "Username already exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Hash the password
                String hashed = String.valueOf(password.hashCode());

                boolean success = db.registerUser(username, "none", hashed);

                if (success) {
                    Toast.makeText(creat_account.this, "Account created!", Toast.LENGTH_SHORT).show();

                    // CREATE SESSION
                    SessionManager session = new SessionManager(creat_account.this);
                    session.createSession(username);
                    
                    Intent intent = new Intent(creat_account.this, introduction.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(creat_account.this, "Error creating account", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
