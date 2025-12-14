package com.example.projetm2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class quizz extends AppCompatActivity {

    Button quizzprv, btnSubmit, btnLogout;
    RadioGroup radioGroup;
    RadioButton rb1, rb2, rb3, rb4;
    TextView tvQuestion, tvBottomNote, comment, tvUsername, tvTimer, tvStudyTime;

    SessionManager session;
    private CountDownTimer timer;
    private long timeLeft = 30000; // 30 seconds
    private boolean answered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        // SESSION
        session = new SessionManager(this);
        if (!session.isLoggedIn()) {
            startActivity(new Intent(quizz.this, log_in.class));
            finish();
            return;
        }

        // START TIME TRACKING SERVICE
        Intent serviceIntent = new Intent(this, TimeTrackingService.class);
        serviceIntent.putExtra("username", session.getUsername());
        startService(serviceIntent);

        // INIT ALL VIEWS
        tvTimer = findViewById(R.id.tvTimer);
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
            stopService(new Intent(this, TimeTrackingService.class));
            session.logout();
            startActivity(new Intent(quizz.this, log_in.class));
            finish();
        });

        quizzprv = findViewById(R.id.btnquizzprevious);
        comment = findViewById(R.id.BottomNote);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvBottomNote = findViewById(R.id.BottomNote);
        radioGroup = findViewById(R.id.radioGroupChoices);
        rb1 = findViewById(R.id.rbChoice1);
        rb2 = findViewById(R.id.rbChoice2);
        rb3 = findViewById(R.id.rbChoice3);
        rb4 = findViewById(R.id.rbChoice4);
        btnSubmit = findViewById(R.id.btnSubmitsQuizz);

        // LOAD QUIZ DATA
        String question = getIntent().getStringExtra("question");
        String p1 = getIntent().getStringExtra("p1");
        String p2 = getIntent().getStringExtra("p2");
        String p3 = getIntent().getStringExtra("p3");
        String p4 = getIntent().getStringExtra("p4");
        String correct = getIntent().getStringExtra("correct");

        tvQuestion.setText(question);
        rb1.setText(p1);
        rb2.setText(p2);
        rb3.setText(p3);
        rb4.setText(p4);

        // START 30-SECOND TIMER
        startTimer();

        // SUBMIT BUTTON
        btnSubmit.setOnClickListener(v -> checkAnswer(correct));

        // PREVIOUS BUTTON
        quizzprv.setOnClickListener(v -> {
            stopTimer();
            if (getIntent().hasExtra("course_type") && "c".equals(getIntent().getStringExtra("course_type"))) {
                startActivity(new Intent(quizz.this, what_to_do_C.class));
            } else {
                startActivity(new Intent(quizz.this, what_to_do.class));
            }
        });
    }

    private void startTimer() {
        timer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                int seconds = (int) (timeLeft / 1000);
                tvTimer.setText(String.valueOf(seconds));

                // Change color when time is low
                if (seconds <= 10) {
                    tvTimer.setTextColor(Color.RED);
                } else if (seconds <= 20) {
                    tvTimer.setTextColor(Color.YELLOW);
                } else {
                    tvTimer.setTextColor(Color.parseColor("#FFD700")); // Gold
                }
            }

            @Override
            public void onFinish() {
                tvTimer.setText("0");
                tvTimer.setTextColor(Color.RED);
                if (!answered) {
                    timeUp();
                }
            }
        }.start();
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private void checkAnswer(String correct) {
        if (answered) return;
        answered = true;
        stopTimer();

        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId == -1) {
            // No answer selected = wrong
            showAnswer(false, correct, null);
            return;
        }

        RadioButton selected = findViewById(selectedId);
        String userAnswer = selected.getText().toString();

        boolean isCorrect = userAnswer.equals(correct);
        showAnswer(isCorrect, correct, selected);
    }

    private void timeUp() {
        answered = true;
        String correct = getIntent().getStringExtra("correct");

        tvBottomNote.setText("Time's up! Correct answer: " + correct);
        tvBottomNote.setTextColor(Color.WHITE);
        comment.setBackgroundColor(Color.RED);

        // Highlight correct answer
        highlightCorrect(correct);

        radioGroup.setEnabled(false);
        btnSubmit.setEnabled(false);
    }

    private void showAnswer(boolean isCorrect, String correct, RadioButton selected) {
        // Reset colors
        rb1.setBackgroundColor(Color.TRANSPARENT);
        rb2.setBackgroundColor(Color.TRANSPARENT);
        rb3.setBackgroundColor(Color.TRANSPARENT);
        rb4.setBackgroundColor(Color.TRANSPARENT);

        if (isCorrect) {
            tvBottomNote.setText("Correct!");
            tvBottomNote.setTextColor(Color.WHITE);
            comment.setBackgroundColor(Color.GREEN);
            if (selected != null) selected.setBackgroundColor(Color.GREEN);
        } else {
            tvBottomNote.setText("Wrong! Correct: " + correct);
            tvBottomNote.setTextColor(Color.WHITE);
            comment.setBackgroundColor(Color.RED);
            if (selected != null) selected.setBackgroundColor(Color.RED);
            highlightCorrect(correct);
        }

        radioGroup.setEnabled(false);
        btnSubmit.setEnabled(false);
    }

    private void highlightCorrect(String correct) {
        if (rb1.getText().toString().equals(correct)) rb1.setBackgroundColor(Color.GREEN);
        else if (rb2.getText().toString().equals(correct)) rb2.setBackgroundColor(Color.GREEN);
        else if (rb3.getText().toString().equals(correct)) rb3.setBackgroundColor(Color.GREEN);
        else if (rb4.getText().toString().equals(correct)) rb4.setBackgroundColor(Color.GREEN);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }
}