package com.example.projetm2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.TextView;


public class quizz extends AppCompatActivity {
    Button quizzprv,btnSubmit;
    RadioGroup radioGroup;
    RadioButton rb1, rb2, rb3, rb4;
    TextView tvQuestion, tvBottomNote,comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
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

        String question = getIntent().getStringExtra("question");
        String p1 = getIntent().getStringExtra("p1");
        String p2 = getIntent().getStringExtra("p2");
        String p3 = getIntent().getStringExtra("p3");
        String p4 = getIntent().getStringExtra("p4");
        String correct = getIntent().getStringExtra("correct");

        String c1 = getIntent().getStringExtra("c1");
        String c2 = getIntent().getStringExtra("c2");
        String c3 = getIntent().getStringExtra("c3");
        String c4 = getIntent().getStringExtra("c4");

        tvQuestion.setText(question);
        rb1.setText(p1);
        rb2.setText(p2);
        rb3.setText(p3);
        rb4.setText(p4);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                if (userAnswer.equals(correct)) {
                    tvBottomNote.setText("correct");
                    tvBottomNote.setTextColor(Color.WHITE);
                    comment.setBackgroundColor(Color.GREEN);
                    selected.setBackgroundColor(Color.GREEN);

                } else {
                    if (selected == rb1) tvBottomNote.setText(c1);
                    if (selected == rb2) tvBottomNote.setText(c2);
                    if (selected == rb3) tvBottomNote.setText(c3);
                    if (selected == rb4) tvBottomNote.setText(c4);
                    tvBottomNote.setTextColor(Color.WHITE);
                    comment.setBackgroundColor(Color.RED);
                    selected.setBackgroundColor(Color.RED);
                }
            }
        });


        quizzprv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb1.setBackgroundColor(Color.TRANSPARENT);
                rb2.setBackgroundColor(Color.TRANSPARENT);
                rb3.setBackgroundColor(Color.TRANSPARENT);
                rb4.setBackgroundColor(Color.TRANSPARENT);
                radioGroup.clearCheck();
                tvBottomNote.setText("");
                comment.setBackgroundColor(Color.TRANSPARENT);
                Intent intent = new Intent(quizz.this, what_to_do.class);
                startActivity(intent);
            }
        });

    }
}