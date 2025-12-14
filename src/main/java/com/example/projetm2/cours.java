package com.example.projetm2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class cours extends AppCompatActivity {

    Button crprv, crnext;
    TextView cour, tvUsername;
    Button btnLogout;

    int index;
    String track;
    List<CourseItem> usedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cours);

        crprv = findViewById(R.id.btncrprevious);
        crnext = findViewById(R.id.btncrnext);
        cour = findViewById(R.id.CourseContent);

        tvUsername = findViewById(R.id.tvUsername);
        btnLogout = findViewById(R.id.btnLogout);
        SessionManager session = new SessionManager(this);
        if (!session.isLoggedIn()) {
            startActivity(new Intent(this, log_in.class));
            finish();
            return;
        }
        tvUsername.setText(session.getUsername());
        btnLogout.setOnClickListener(v -> {
            session.logout();
            startActivity(new Intent(cours.this, log_in.class));
            finish();
        });

        index = getIntent().getIntExtra("index", 0);
        track = getIntent().getStringExtra("track");
        usedList = "c".equals(track) ? GlobalContent.cList : GlobalContent.algoList;

        if (usedList == null || index < 0 || index >= usedList.size()) {
            cour.setText("Content not available.");
            return;
        }

        CourseItem item = usedList.get(index);
        if (item.isQuiz()) {
            Intent intent = new Intent(this, quizz.class);
            fillQuizIntent(intent, item);
            intent.putExtra("index", index);
            intent.putExtra("track", track);
            startActivity(intent);
            finish();
            return;
        }

        cour.setText(Html.fromHtml(item.getContent(), Html.FROM_HTML_MODE_LEGACY));

        crprv.setOnClickListener(v -> navigateCourse(index - 1));
        if (crnext != null) crnext.setOnClickListener(v -> navigateCourse(index + 1));
    }

    private void navigateCourse(int newIndex) {
        if (usedList == null || newIndex < 0 || newIndex >= usedList.size()) return;
        CourseItem item = usedList.get(newIndex);
        Intent intent = item.isQuiz() ? new Intent(this, quizz.class) : new Intent(this, cours.class);
        if (item.isQuiz()) fillQuizIntent(intent, item);
        else intent.putExtra("cours", item.getContent());
        intent.putExtra("index", newIndex);
        intent.putExtra("track", track);
        startActivity(intent);
        finish();
    }

    private void fillQuizIntent(Intent intent, CourseItem item) {
        intent.putExtra("question", item.getQuestion());
        intent.putExtra("p1", item.getP1());
        intent.putExtra("p2", item.getP2());
        intent.putExtra("p3", item.getP3());
        intent.putExtra("p4", item.getP4());
        intent.putExtra("correct", item.getCorrect());
        intent.putExtra("c1", item.getC1());
        intent.putExtra("c2", item.getC2());
        intent.putExtra("c3", item.getC3());
        intent.putExtra("c4", item.getC4());
    }
}
