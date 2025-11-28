package com.example.projetm2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class what_to_do_C extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CourseAdapter adapter;
    private List<CourseItem> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_to_do_c_recycler);

        String cours_recu = getIntent().getStringExtra("cours");

        recyclerView = findViewById(R.id.recyclerViewCourses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseList = new ArrayList<>();
        setupCCourses();

        adapter = new CourseAdapter(courseList, false);
        recyclerView.setAdapter(adapter);

        Button goToAlgo = findViewById(R.id.btnGoToAlgo);
        goToAlgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(what_to_do_C.this, what_to_do.class);
                intent.putExtra("cours", cours_recu);
                startActivity(intent);
            }
        });
    }

    private void setupCCourses() {
        courseList.add(new CourseItem("Introduction to C", getString(R.string.cours1_C)));
        courseList.add(new CourseItem("Installing and Using a C Compiler", getString(R.string.cours2_C)));
        courseList.add(new CourseItem("Structure of a C Program", getString(R.string.cours3_C)));
        courseList.add(new CourseItem("Variables in C", getString(R.string.cours4_C)));
        courseList.add(new CourseItem("Data Types in C", getString(R.string.cours5_C)));
        courseList.add(new CourseItem("Operators in C", getString(R.string.cours6_C)));
        courseList.add(new CourseItem("Input and Output", getString(R.string.cours7_C)));
        courseList.add(new CourseItem("IF / ELSE Conditions", getString(R.string.cours8_C)));
        courseList.add(new CourseItem("SWITCH / CASE", getString(R.string.cours9_C)));
        courseList.add(new CourseItem("FOR Loop", getString(R.string.cours10_C)));
        courseList.add(new CourseItem("WHILE Loop", getString(R.string.cours11_C)));
        courseList.add(new CourseItem("DO…WHILE Loop", getString(R.string.cours12_C)));
        courseList.add(new CourseItem("Arrays", getString(R.string.cours13_C)));
        courseList.add(new CourseItem("Introduction to Pointers", getString(R.string.cours14_C)));
        courseList.add(new CourseItem("Structures", getString(R.string.cours15_C)));
        courseList.add(new CourseItem("Linked Lists", getString(R.string.cours16_C)));
        courseList.add(new CourseItem("Functions and Procedures", getString(R.string.cours17_C)));
    }
}