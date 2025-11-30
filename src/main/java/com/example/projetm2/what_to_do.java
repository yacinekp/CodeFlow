package com.example.projetm2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class what_to_do extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CourseAdapter adapter;
    private List<CourseItem> courseList;

    SessionManager session;
    TextView tvUsername;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_to_do_recycler);

        // Session
        session = new SessionManager(this);
        if (!session.isLoggedIn()) {
            startActivity(new Intent(this, log_in.class));
            finish();
            return;
        }

        // UI
        tvUsername = findViewById(R.id.tvUsername);
        btnLogout = findViewById(R.id.btnLogout);

        tvUsername.setText(session.getUsername());
        btnLogout.setOnClickListener(v -> {
            session.logout();
            startActivity(new Intent(what_to_do.this, log_in.class));
            finish();
        });

        recyclerView = findViewById(R.id.recyclerViewCourses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseList = new ArrayList<>();
        setupAlgorithmicCourses();

        adapter = new CourseAdapter(courseList, true);
        recyclerView.setAdapter(adapter);

        Button goToC = findViewById(R.id.btnGoToC);
        goToC.setOnClickListener(v -> {
            startActivity(new Intent(what_to_do.this, what_to_do_C.class));
        });
    }

    private void setupAlgorithmicCourses() {
        courseList.add(new CourseItem("Introduction to Algorithms", getString(R.string.cours1)));
        courseList.add(new CourseItem("Variables", getString(R.string.cours2)));
        courseList.add(new CourseItem("Data Types", getString(R.string.cours3)));
        courseList.add(new CourseItem("Operators", getString(R.string.cours4)));
        courseList.add(new CourseItem("Input/Output", getString(R.string.cours5)));
        courseList.add(new CourseItem("IF / ELSE", getString(R.string.cours6)));
        courseList.add(new CourseItem("SWITCH / CASE", getString(R.string.cours7)));
        courseList.add(new CourseItem("FOR Loop", getString(R.string.cours8)));
        courseList.add(new CourseItem("WHILE Loop", getString(R.string.cours9)));
        courseList.add(new CourseItem("REPEAT Loop", getString(R.string.cours10)));
        courseList.add(new CourseItem("Arrays", getString(R.string.cours11)));
        courseList.add(new CourseItem("Pointers", getString(R.string.cours12)));
        courseList.add(new CourseItem("Records", getString(R.string.cours13)));
        courseList.add(new CourseItem("Lists", getString(R.string.cours14)));
        courseList.add(new CourseItem("Subprograms", getString(R.string.cours15)));
        courseList.add(new CourseItem("Problem-Solving", getString(R.string.cours16)));
        courseList.add(new CourseItem("Exercises", getString(R.string.cours17)));
        courseList.add(new CourseItem("Sorting", getString(R.string.cours18)));
    }
}
