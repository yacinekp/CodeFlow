package com.example.projetm2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class cours extends AppCompatActivity {
    Button crprv;
    Button crnext;
    TextView cour;
    TextView tvUsername;
    Button btnLogout;

    int index;
    String track;
    List<CourseItem> usedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cours);

        crprv = findViewById(R.id.btncrprevious);
        crnext = findViewById(R.id.btncrnext); // may be null if your XML doesn't have it yet
        cour = findViewById(R.id.CourseContent);

        // session UI
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
            // If this index actually points to a quiz, redirect
            Intent intent = new Intent(this, quizz.class);
            fillQuizIntent(intent, item);
            intent.putExtra("index", index);
            intent.putExtra("track", track);
            startActivity(intent);
            finish();
            return;
        }

        String coursHtml = item.getContent();
        if (coursHtml != null) {
            cour.setText(Html.fromHtml(coursHtml, Html.FROM_HTML_MODE_LEGACY));
        }

        crprv.setOnClickListener(v -> {
            int prev = index - 1;
            if (prev >= 0) {
                CourseItem prevItem = usedList.get(prev);
                if (prevItem.isQuiz()) {
                    Intent intent = new Intent(cours.this, quizz.class);
                    fillQuizIntent(intent, prevItem);
                    intent.putExtra("index", prev);
                    intent.putExtra("track", track);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(cours.this, cours.class);
                    intent.putExtra("cours", prevItem.getContent());
                    intent.putExtra("index", prev);
                    intent.putExtra("track", track);
                    startActivity(intent);
                }
                finish();
            }
        });

        if (crnext != null) {
            crnext.setOnClickListener(v -> {
                int next = index + 1;
                if (next < usedList.size()) {
                    CourseItem nextItem = usedList.get(next);
                    if (nextItem.isQuiz()) {
                        Intent intent = new Intent(cours.this, quizz.class);
                        fillQuizIntent(intent, nextItem);
                        intent.putExtra("index", next);
                        intent.putExtra("track", track);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(cours.this, cours.class);
                        intent.putExtra("cours", nextItem.getContent());
                        intent.putExtra("index", next);
                        intent.putExtra("track", track);
                        startActivity(intent);
                    }
                    finish();
                }
            });
        }
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
