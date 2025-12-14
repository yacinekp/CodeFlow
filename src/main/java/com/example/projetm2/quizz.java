package com.example.projetm2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class quizz extends AppCompatActivity {

    Button quizzprv, btnSubmit, quizzNext;
    RadioGroup radioGroup;
    RadioButton rb1, rb2, rb3, rb4;
    TextView tvQuestion, tvBottomNote, comment, tvUsername;
    Button btnLogout;

    int index;
    String track;
    List<CourseItem> usedList;
    CourseItem current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        quizzprv = findViewById(R.id.btnquizzprevious);
        quizzNext = findViewById(R.id.btnquizznext);
        comment = findViewById(R.id.BottomNote);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvBottomNote = findViewById(R.id.BottomNote);
        radioGroup = findViewById(R.id.radioGroupChoices);
        rb1 = findViewById(R.id.rbChoice1);
        rb2 = findViewById(R.id.rbChoice2);
        rb3 = findViewById(R.id.rbChoice3);
        rb4 = findViewById(R.id.rbChoice4);
        btnSubmit = findViewById(R.id.btnSubmitsQuizz);

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
            startActivity(new Intent(quizz.this, log_in.class));
            finish();
        });

        index = getIntent().getIntExtra("index", 0);
        track = getIntent().getStringExtra("track");
        usedList = "c".equals(track) ? GlobalContent.cList : GlobalContent.algoList;

        if (usedList == null || index < 0 || index >= usedList.size()) {
            tvQuestion.setText("Quiz not available.");
            return;
        }

        current = usedList.get(index);
        if (!current.isQuiz()) {
            Intent intent = new Intent(this, cours.class);
            intent.putExtra("cours", current.getContent());
            intent.putExtra("index", index);
            intent.putExtra("track", track);
            startActivity(intent);
            finish();
            return;
        }

        tvQuestion.setText(current.getQuestion());
        rb1.setText(current.getP1());
        rb2.setText(current.getP2());
        rb3.setText(current.getP3());
        rb4.setText(current.getP4());

        btnSubmit.setOnClickListener(v -> checkAnswer());

        quizzprv.setOnClickListener(v -> navigateQuiz(index - 1));
        if (quizzNext != null) quizzNext.setOnClickListener(v -> navigateQuiz(index + 1));
    }

    private void checkAnswer() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            tvBottomNote.setText("Please select an answer!");
            return;
        }

        RadioButton selected = findViewById(selectedId);
        String userAnswer = selected.getText().toString();

        rb1.setBackgroundColor(Color.TRANSPARENT);
        rb2.setBackgroundColor(Color.TRANSPARENT);
        rb3.setBackgroundColor(Color.TRANSPARENT);
        rb4.setBackgroundColor(Color.TRANSPARENT);

        if (userAnswer.equals(current.getCorrect())) {
            tvBottomNote.setText("Correct");
            tvBottomNote.setTextColor(Color.WHITE);
            comment.setBackgroundColor(Color.GREEN);
            selected.setBackgroundColor(Color.GREEN);
        } else {
            if (selected == rb1) tvBottomNote.setText(current.getC1());
            if (selected == rb2) tvBottomNote.setText(current.getC2());
            if (selected == rb3) tvBottomNote.setText(current.getC3());
            if (selected == rb4) tvBottomNote.setText(current.getC4());
            tvBottomNote.setTextColor(Color.WHITE);
            comment.setBackgroundColor(Color.RED);
            selected.setBackgroundColor(Color.RED);
        }
    }

    private void navigateQuiz(int newIndex) {
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
