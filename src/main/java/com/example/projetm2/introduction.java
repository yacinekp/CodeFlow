package com.example.projetm2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class introduction extends AppCompatActivity {

    Button next_intro, prv_intro;
    TextView introText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        next_intro = findViewById(R.id.btnNextIntro);
        prv_intro = findViewById(R.id.btnpreviousintro);
        introText = findViewById(R.id.introText);

        introText.setText(Html.fromHtml(getString(R.string.intro), Html.FROM_HTML_MODE_LEGACY));

        next_intro.setOnClickListener(v -> {
            Intent intent = new Intent(introduction.this, choose.class);
            startActivity(intent);
        });

        prv_intro.setOnClickListener(v -> {
            Intent intent = new Intent(introduction.this, creat_account.class);
            startActivity(intent);
        });
    }
}
