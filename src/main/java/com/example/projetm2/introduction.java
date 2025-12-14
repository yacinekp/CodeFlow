package com.example.projetm2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class introduction extends AppCompatActivity {

    Button next_intro, prv_intro, btnLogout;
    TextView introText, tvUsername, tvStudyTime;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

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

        next_intro = findViewById(R.id.btnNextIntro);
        prv_intro = findViewById(R.id.btnpreviousintro);
        introText = findViewById(R.id.introText);
        tvUsername = findViewById(R.id.tvUsername);
        btnLogout = findViewById(R.id.btnLogout);
//        tvStudyTime = findViewById(R.id.tvStudyTime);

        tvUsername.setText(session.getUsername());

        // SHOW STUDY TIME
//        database_Handler db = new database_Handler(this);
//        long totalSeconds = db.getStudyTime(session.getUsername());
//        long hours = totalSeconds / 3600;
//        long minutes = (totalSeconds % 3600) / 60;
//        long seconds = totalSeconds % 60;
//        tvStudyTime.setText("Study Time: " + hours + "h " + minutes + "m " + seconds + "s");

        introText.setText(Html.fromHtml(getString(R.string.intro), Html.FROM_HTML_MODE_LEGACY));

        btnLogout.setOnClickListener(v -> {
            // STOP TIME TRACKING SERVICE
            stopService(new Intent(introduction.this, TimeTrackingService.class));
            session.logout();
            Intent intent = new Intent(introduction.this, log_in.class);
            startActivity(intent);
            finish();
        });

        next_intro.setOnClickListener(v -> {
            Intent intent = new Intent(introduction.this, choose.class);
            startActivity(intent);
            finish();
        });

        prv_intro.setOnClickListener(v -> {
            // FIXED: Go to login, don't logout
            startActivity(new Intent(introduction.this, log_in.class));
            finish();
        });
    }
}