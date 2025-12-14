package com.example.projetm2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class choose extends AppCompatActivity {

    SessionManager session;
    TextView tvUsername, tvStudyTime;
    Button btnLogout, btnAlgorithmic, btnCLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        // Session check
        session = new SessionManager(this);
        if (!session.isLoggedIn()) {
            startActivity(new Intent(this, log_in.class));
            finish();
            return;
        }

        // START TIME TRACKING SERVICE
        Intent serviceIntent = new Intent(this, TimeTrackingService.class);
        serviceIntent.putExtra("username", session.getUsername());
        startService(serviceIntent);

        tvUsername = findViewById(R.id.tvUsername);
        btnLogout = findViewById(R.id.btnLogout);
//        tvStudyTime = findViewById(R.id.tvStudyTime);
        btnAlgorithmic = findViewById(R.id.btnAlgorithmic);
        btnCLanguage = findViewById(R.id.btnCLanguage);

        tvUsername.setText(session.getUsername());

        // SHOW STUDY TIME
//        database_Handler db = new database_Handler(this);
//        long totalSeconds = db.getStudyTime(session.getUsername());
//        long hours = totalSeconds / 3600;
//        long minutes = (totalSeconds % 3600) / 60;
//        long seconds = totalSeconds % 60;
//        tvStudyTime.setText("Study Time: " + hours + "h " + minutes + "m " + seconds + "s");

        btnLogout.setOnClickListener(v -> {
            // STOP TIME TRACKING SERVICE
            stopService(new Intent(choose.this, TimeTrackingService.class));
            session.logout();
            startActivity(new Intent(choose.this, log_in.class));
            finish();
        });

        btnAlgorithmic.setOnClickListener(v -> {
            startActivity(new Intent(choose.this, what_to_do.class));
        });

        btnCLanguage.setOnClickListener(v -> {
            startActivity(new Intent(choose.this, what_to_do_C.class));
        });
    }
}