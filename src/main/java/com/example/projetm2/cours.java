package com.example.projetm2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class cours extends AppCompatActivity {

    Button crprv, btnLogout;
    TextView cour, tvUsername, tvStudyTime;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cours);

        // SESSION MANAGEMENT
        session = new SessionManager(this);
        if (!session.isLoggedIn()) {
            startActivity(new Intent(cours.this, log_in.class));
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
            stopService(new Intent(cours.this, TimeTrackingService.class));
            session.logout();
            Intent intent = new Intent(cours.this, log_in.class);
            startActivity(intent);
            finish();
        });

        // CONTENT UI
        crprv = findViewById(R.id.btncrprevious);
        cour = findViewById(R.id.CourseContent);

        // Load content from intent
        String coursTxt = getIntent().getStringExtra("cours");
        if (coursTxt != null) {
            cour.setText(Html.fromHtml(coursTxt, Html.FROM_HTML_MODE_LEGACY));
        }

        // Previous button
        crprv.setOnClickListener(v -> {
            if (getIntent().hasExtra("course_type") && "c".equals(getIntent().getStringExtra("course_type"))) {
                startActivity(new Intent(cours.this, what_to_do_C.class));
            } else {
                startActivity(new Intent(cours.this, what_to_do.class));
            }
        });
    }
}