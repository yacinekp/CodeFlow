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

public class what_to_do_C extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CourseQuizAdapter adapter;
    private List<CourseItem> courseList;

    SessionManager session;
    TextView tvUsername;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_to_do_c_recycler);

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
            startActivity(new Intent(what_to_do_C.this, log_in.class));
            finish();
        });

        recyclerView = findViewById(R.id.recyclerViewCourses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseList = new ArrayList<>();
        setupCCoursesMixed(courseList);

        GlobalContent.cList = courseList;

        adapter = new CourseQuizAdapter(what_to_do_C.this, courseList);
        recyclerView.setAdapter(adapter);

        Button goToAlgo = findViewById(R.id.btnGoToAlgo);
        goToAlgo.setOnClickListener(v -> {
            startActivity(new Intent(what_to_do_C.this, what_to_do.class));
        });
    }

    private void setupCCoursesMixed(List<CourseItem> list) {
        // Course 1
        list.add(new CourseItem("Introduction to C", getString(R.string.cours1_C)));
        // Quiz C1 (original)
        list.add(new CourseItem("Quiz C1",
                "Why is C a popular programming language?",
                "A. It is slow but easy to use",
                "B. It is close to the hardware, fast, and efficient",
                "C. It cannot be used for software development",
                "D. It only works on Windows",
                "B. It is close to the hardware, fast, and efficient",
                "Incorrect.",
                "Correct!",
                "Incorrect.",
                "Incorrect."));
        // Course 2
        list.add(new CourseItem("Installing and Using a C Compiler", getString(R.string.cours2_C)));
        // Quiz C2 (original)
        list.add(new CourseItem("Quiz C2",
                "What is the role of a C compiler?",
                "A. It writes code for you automatically",
                "B. It translates your C code into a program your computer can run",
                "C. It stores variables in memory",
                "D. It checks the internet for solutions",
                "B. It translates your C code into a program your computer can run",
                "Incorrect.",
                "Correct!",
                "Incorrect.",
                "Incorrect."));
        // Course 3
        list.add(new CourseItem("Structure of a C Program", getString(R.string.cours3_C)));
        // Quiz C3 - small example
        list.add(new CourseItem("Quiz C3",
                "What is the main function in a C program?",
                "A. A section to store variables only",
                "B. The entry point where program execution starts",
                "C. A loop that repeats instructions",
                "D. A library that prints text",
                "B. The entry point where program execution starts",
                "Incorrect.",
                "Correct!",
                "Incorrect.",
                "Incorrect."));
        // Course 4
        list.add(new CourseItem("Variables in C", getString(R.string.cours4_C)));
        // Quiz C4
        list.add(new CourseItem("Quiz C4",
                "What is true about variables in C?",
                "A. They store information and can change during program execution",
                "B. They are always constant and cannot change",
                "C. They are only used to write text on the screen",
                "D. They are loops that repeat instructions",
                "A. They store information and can change during program execution",
                "Correct!",
                "Incorrect.",
                "Incorrect.",
                "Incorrect."));
        // Course 5
        list.add(new CourseItem("Data Types in C", getString(R.string.cours5_C)));
        // Quiz C5
        list.add(new CourseItem("Quiz C5",
                "What is the purpose of data types in C?",
                "A. To tell the computer how much memory a variable needs, what type of value it stores, and allowed operations",
                "B. To make programs run without a compiler",
                "C. To store multiple values in one variable automatically",
                "D. To create new operators",
                "A. To tell the computer how much memory a variable needs, what type of value it stores, and allowed operations",
                "Correct!",
                "Incorrect.",
                "Incorrect.",
                "Incorrect."));
        // Course 6
        list.add(new CourseItem("Operators in C", getString(R.string.cours6_C)));
        // Quiz C6
        list.add(new CourseItem("Quiz C6",
                "What does the modulo operator (%) return?",
                "A. The opposite of a number",
                "B. The remainder of a division",
                "C. The result of addition",
                "D. The number of variables",
                "B. The remainder of a division",
                "Incorrect.",
                "Correct!",
                "Incorrect.",
                "Incorrect."));
        // Course 7
        list.add(new CourseItem("Input and Output", getString(R.string.cours7_C)));
        // Quiz C7
        list.add(new CourseItem("Quiz C7",
                "What does the scanf function do?",
                "A. Displays text on the screen",
                "B. Reads a value entered by the user",
                "C. Performs calculations",
                "D. Stops the program",
                "B. Reads a value entered by the user",
                "Incorrect.",
                "Correct!",
                "Incorrect.",
                "Incorrect."));
        // Course 8
        list.add(new CourseItem("IF / ELSE Conditions", getString(R.string.cours8_C)));
        // Quiz C8
        list.add(new CourseItem("Quiz C8",
                "What is the purpose of IF / ELSE?",
                "A. To repeat actions many times",
                "B. To store multiple values",
                "C. To choose between two or more actions based on a condition",
                "D. To declare variables",
                "C. To choose between two or more actions based on a condition",
                "Incorrect.",
                "Correct!",
                "Incorrect.",
                "Incorrect."));
        // Course 9
        list.add(new CourseItem("SWITCH / CASE", getString(R.string.cours9_C)));
        // Quiz C9
        list.add(new CourseItem("Quiz C9",
                "When should you use SWITCH / CASE?",
                "A. When choosing between many options based on a single variable",
                "B. When you want to repeat code multiple times",
                "C. When storing many numbers",
                "D. When comparing two variables only",
                "A. When choosing between many options based on a single variable",
                "Incorrect.",
                "Correct!",
                "Incorrect.",
                "Incorrect."));
        // Course 10
        list.add(new CourseItem("FOR Loop", getString(R.string.cours10_C)));
        // Quiz C10
        list.add(new CourseItem("Quiz C10",
                "When do we use a FOR loop?",
                "A. When we want to repeat an action a fixed number of times",
                "B. When we want to run a loop only once",
                "C. When we want to choose between options",
                "D. When reading input from the user only",
                "A. When we want to repeat an action a fixed number of times",
                "Correct!",
                "Incorrect.",
                "Incorrect.",
                "Incorrect."));
        // Course 11
        list.add(new CourseItem("WHILE Loop", getString(R.string.cours11_C)));
        // Quiz C11
        list.add(new CourseItem("Quiz C11",
                "What is the key feature of a WHILE loop?",
                "A. Repeats actions as long as the condition is true",
                "B. Always runs exactly once",
                "C. Declares variables automatically",
                "D. Sorts numbers",
                "A. Repeats actions as long as the condition is true",
                "Correct!",
                "Incorrect.",
                "Incorrect.",
                "Incorrect."));
        // Course 12
        list.add(new CourseItem("DO…WHILE Loop", getString(R.string.cours12_C)));
        // Quiz C12
        list.add(new CourseItem("Quiz C12",
                "What is special about a DO…WHILE loop?",
                "A. It never stops",
                "B. It always runs at least once before checking the condition",
                "C. It checks the condition before running",
                "D. It can only work with numbers",
                "B. It always runs at least once before checking the condition",
                "Incorrect.",
                "Correct!",
                "Incorrect.",
                "Incorrect."));
        // Course 13
        list.add(new CourseItem("Arrays", getString(R.string.cours13_C)));
        // Quiz C13
        list.add(new CourseItem("Quiz C13",
                "What is an array in C?",
                "A. A single number stored in memory",
                "B. A structure that stores multiple values of the same type",
                "C. A loop that repeats instructions",
                "D. A variable that stores text only",
                "B. A structure that stores multiple values of the same type",
                "Incorrect.",
                "Correct!",
                "Incorrect.",
                "Incorrect."));
        // Course 14
        list.add(new CourseItem("Introduction to Pointers", getString(R.string.cours14_C)));
        // Quiz C14
        list.add(new CourseItem("Quiz C14",
                "What does a pointer store?",
                "A. A true/false value",
                "B. A text string",
                "C. The address of another variable in memory",
                "D. A number only",
                "C. The address of another variable in memory",
                "Incorrect.",
                "Correct!",
                "Incorrect.",
                "Incorrect."));
        // Course 15
        list.add(new CourseItem("Structures", getString(R.string.cours15_C)));
        // Quiz C15
        list.add(new CourseItem("Quiz C15",
                "What is a structure (record) in C?",
                "A. A variable that stores only integers",
                "B. A data type that groups different variables together",
                "C. A loop that repeats instructions",
                "D. A pointer to a variable",
                "B. A data type that groups different variables together",
                "Incorrect.",
                "Correct!",
                "Incorrect.",
                "Incorrect."));
        // Course 16
        list.add(new CourseItem("Linked Lists", getString(R.string.cours16_C)));
        // Quiz C16
        list.add(new CourseItem("Quiz C16",
                "What is a linked list?",
                "A. A static array",
                "B. A structure where each element contains a value and a pointer to the next",
                "C. A loop that repeats several actions",
                "D. A function that returns numbers",
                "B. A structure where each element contains a value and a pointer to the next",
                "Incorrect.",
                "Correct!",
                "Incorrect.",
                "Incorrect."));
        // Course 17
        list.add(new CourseItem("Functions and Procedures", getString(R.string.cours17_C)));
        // Quiz C17
        list.add(new CourseItem("Quiz C17",
                "What is a subprogram in C?",
                "A. A small part of a program that performs a specific task",
                "B. A variable that stores many values",
                "C. A loop that never ends",
                "D. A list of random steps",
                "A. A small part of a program that performs a specific task",
                "Incorrect.",
                "Correct!",
                "Incorrect.",
                "Incorrect."));
    }
}
