package com.example.projetm2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class creat_account extends AppCompatActivity {

    EditText etUsername, etEmail, etPassword, etConfirmPassword;
    TextView tvUsernameError, tvEmailError, tvPasswordError, tvConfirmPasswordError;
    Button create, btnGoToLogin;

    database_Handler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_account);

        db = new database_Handler(this);

        // Initialize EditTexts
        etUsername = findViewById(R.id.CreateAccountUserName);
        etEmail = findViewById(R.id.btnCreateAccountEmail);
        etPassword = findViewById(R.id.btnCreateAccountPassword);
        etConfirmPassword = findViewById(R.id.btnCreateAccountConfirmPassword);

        // Initialize Error TextViews
        tvUsernameError = findViewById(R.id.tvUsernameError);
        tvEmailError = findViewById(R.id.tvEmailError);
        tvPasswordError = findViewById(R.id.tvPasswordError);
        tvConfirmPasswordError = findViewById(R.id.tvConfirmPasswordError);

        // Initialize Buttons
        create = findViewById(R.id.btnCreate);
        btnGoToLogin = findViewById(R.id.btnGoToLogin);

        // Clear errors when user starts typing
        setupClearErrorOnType(etUsername, tvUsernameError);
        setupClearErrorOnType(etEmail, tvEmailError);
        setupClearErrorOnType(etPassword, tvPasswordError);
        setupClearErrorOnType(etConfirmPassword, tvConfirmPasswordError);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear all previous errors
                clearAllErrors();

                String username = etUsername.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString();
                String confirm = etConfirmPassword.getText().toString();

                boolean isValid = true;

                // 1. Validate Username
                if (TextUtils.isEmpty(username)) {
                    showError(tvUsernameError, "Username is required");
                    highlightField(etUsername, true);
                    isValid = false;
                } else if (username.length() < 3) {
                    showError(tvUsernameError, "Username must be at least 3 characters");
                    highlightField(etUsername, true);
                    isValid = false;
                } else if (db.usernameExists(username)) {
                    showError(tvUsernameError, "Username already exists");
                    highlightField(etUsername, true);
                    isValid = false;
                }

                // 2. Validate Email
                if (TextUtils.isEmpty(email)) {
                    showError(tvEmailError, "Email is required");
                    highlightField(etEmail, true);
                    isValid = false;
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    showError(tvEmailError, "Enter a valid email address");
                    highlightField(etEmail, true);
                    isValid = false;
                } else if (db.emailExists(email)) {
                    showError(tvEmailError, "Email already registered");
                    highlightField(etEmail, true);
                    isValid = false;
                }

                // 3. Validate Password
                if (TextUtils.isEmpty(password)) {
                    showError(tvPasswordError, "Password is required");
                    highlightField(etPassword, true);
                    isValid = false;
                } else {
                    String passwordError = validatePassword(password);
                    if (passwordError != null) {
                        showError(tvPasswordError, passwordError);
                        highlightField(etPassword, true);
                        isValid = false;
                    }
                }

                // 4. Validate Confirm Password
                if (TextUtils.isEmpty(confirm)) {
                    showError(tvConfirmPasswordError, "Please confirm your password");
                    highlightField(etConfirmPassword, true);
                    isValid = false;
                } else if (!password.equals(confirm)) {
                    showError(tvConfirmPasswordError, "Passwords do not match");
                    highlightField(etConfirmPassword, true);
                    isValid = false;
                }

                if (!isValid) {
                    // Scroll to first error
                    scrollToFirstError();
                    return;
                }

                // All validations passed - Create account
                String hashed = String.valueOf(password.hashCode());
                boolean success = db.registerUser(username, email, hashed);

                if (success) {
                    // Show success message
                    showSuccessMessage("Account created successfully!");

                    // CREATE SESSION
                    SessionManager session = new SessionManager(creat_account.this);
                    session.createSession(username);

                    // START TIME TRACKING SERVICE
                    Intent serviceIntent = new Intent(creat_account.this, TimeTrackingService.class);
                    serviceIntent.putExtra("username", username);
                    startService(serviceIntent);

                    // Navigate after short delay
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    Intent intent = new Intent(creat_account.this, introduction.class);
                                    startActivity(intent);
                                    finish();
                                }
                            },
                            1500);
                } else {
                    showError(tvUsernameError, "Error creating account. Please try again.");
                }
            }
        });

        btnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(creat_account.this, log_in.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setupClearErrorOnType(EditText editText, final TextView errorView) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    errorView.setVisibility(View.GONE);
                    highlightField((EditText) v, false);
                }
            }
        });

        editText.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                errorView.setVisibility(View.GONE);
                highlightField(editText, false);
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });
    }

    private void showError(TextView errorView, String message) {
        errorView.setText(message);
        errorView.setVisibility(View.VISIBLE);
        errorView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
    }

    // FIXED METHOD: No custom background reference
    private void highlightField(EditText field, boolean hasError) {
        if (hasError) {
            field.setError(" "); // Show error icon
            field.setBackgroundColor(0x99FFFFFF); // Light purple (matches purple bg) // Light red background (34 opacity)
        } else {
            field.setError(null); // Clear error
            field.setBackgroundResource(android.R.drawable.edit_text); // Default background
        }
    }

    // FIXED METHOD: Reset all fields properly
    private void clearAllErrors() {
        tvUsernameError.setVisibility(View.GONE);
        tvEmailError.setVisibility(View.GONE);
        tvPasswordError.setVisibility(View.GONE);
        tvConfirmPasswordError.setVisibility(View.GONE);

        // Clear error states from all fields
        etUsername.setError(null);
        etEmail.setError(null);
        etPassword.setError(null);
        etConfirmPassword.setError(null);

        // Reset backgrounds to default
        etUsername.setBackgroundResource(android.R.drawable.edit_text);
        etEmail.setBackgroundResource(android.R.drawable.edit_text);
        etPassword.setBackgroundResource(android.R.drawable.edit_text);
        etConfirmPassword.setBackgroundResource(android.R.drawable.edit_text);
    }

    private void showSuccessMessage(String message) {
        android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show();
    }

    private void scrollToFirstError() {
        // Scroll to the first error field
        if (tvUsernameError.getVisibility() == View.VISIBLE) {
            etUsername.requestFocus();
        } else if (tvEmailError.getVisibility() == View.VISIBLE) {
            etEmail.requestFocus();
        } else if (tvPasswordError.getVisibility() == View.VISIBLE) {
            etPassword.requestFocus();
        } else if (tvConfirmPasswordError.getVisibility() == View.VISIBLE) {
            etConfirmPassword.requestFocus();
        }
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