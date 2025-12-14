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

        adapter = new CourseQuizAdapter(what_to_do_C.this, courseList,"c");
        recyclerView.setAdapter(adapter);

        Button goToAlgo = findViewById(R.id.btnGoToAlgo);
        goToAlgo.setOnClickListener(v ->
                startActivity(new Intent(what_to_do_C.this, what_to_do.class))
        );
    }

    private void setupCCoursesMixed(List<CourseItem> list) {

        // ---- COURSE 1 + QUIZ C1 ----
        list.add(new CourseItem("Introduction to C", getString(R.string.cours1_C)));

        list.add(new CourseItem("Quiz C1",
                "Why is C a popular programming language?",
                "A. It is slow but easy to use",
                "B. It is close to the hardware, fast, and efficient",
                "C. It cannot be used for software development",
                "D. It only works on Windows",
                "B. It is close to the hardware, fast, and efficient",
                "No. C is known for speed and efficiency, not slowness.",
                "Correct!",
                "No. C is widely used to develop all kinds of software.",
                "No. C works on all major operating systems."
        ));

        // ---- COURSE 2 + QUIZ C2 ----
        list.add(new CourseItem("Installing and Using a C Compiler", getString(R.string.cours2_C)));

        list.add(new CourseItem("Quiz C2",
                "What is the role of a C compiler?",
                "A. It writes code for you automatically",
                "B. It translates your C code into a program your computer can run",
                "C. It stores variables in memory",
                "D. It checks the internet for solutions",
                "B. It translates your C code into a program your computer can run",
                "No. A compiler does not generate code on its own.",
                "Correct!",
                "No. Memory storage happens during execution.",
                "No. A compiler never accesses the internet."
        ));

        // ---- COURSE 3 + QUIZ C3 ----
        list.add(new CourseItem("Structure of a C Program", getString(R.string.cours3_C)));

        list.add(new CourseItem("Quiz C3",
                "What is the main function in a C program?",
                "A. A section to store variables only",
                "B. The entry point where program execution starts",
                "C. A loop that repeats instructions",
                "D. A library that prints text",
                "B. The entry point where program execution starts",
                "No. Variables can be declared anywhere.",
                "Correct!",
                "No. The main function is not a loop.",
                "No. Libraries are separate from main."
        ));

        // ---- COURSE 4 + QUIZ C4 ----
        list.add(new CourseItem("Variables in C", getString(R.string.cours4_C)));

        list.add(new CourseItem("Quiz C4",
                "What is true about variables in C?",
                "A. They store information and can change during program execution",
                "B. They are always constant and cannot change",
                "C. They are only used to write text on the screen",
                "D. They are loops that repeat instructions",
                "A. They store information and can change during program execution",
                "Correct!",
                "No. Variables can change unless constant.",
                "No. Variables store any type of data.",
                "No. Variables are not control structures."
        ));

        // ---- COURSE 5 + QUIZ C5 ----
        list.add(new CourseItem("Data Types in C", getString(R.string.cours5_C)));

        list.add(new CourseItem("Quiz C5",
                "What is the purpose of data types in C?",
                "A. To tell the computer how much memory a variable needs…",
                "B. To make programs run without a compiler",
                "C. To store multiple values automatically",
                "D. To create new operators",
                "A. To tell the computer how much memory a variable needs…",
                "Correct!",
                "No. Programs still need a compiler.",
                "No. Only arrays or structs store multiple values.",
                "No. Operators are predefined."
        ));

        // ---- COURSE 6 + QUIZ C6 ----
        list.add(new CourseItem("Operators in C", getString(R.string.cours6_C)));

        list.add(new CourseItem("Quiz C6",
                "What does the modulo operator (%) return?",
                "A. The opposite of a number",
                "B. The remainder of a division",
                "C. The result of addition",
                "D. The number of variables",
                "B. The remainder of a division",
                "No. % does not invert values.",
                "Correct!",
                "No. + is addition.",
                "No. % is unrelated to counting."
        ));

        // ---- COURSE 7 + QUIZ C7 ----
        list.add(new CourseItem("Input and Output", getString(R.string.cours7_C)));

        list.add(new CourseItem("Quiz C7",
                "What does scanf do?",
                "A. Displays text",
                "B. Reads a value entered by the user",
                "C. Performs calculations",
                "D. Stops the program",
                "B. Reads a value entered by the user",
                "No. printf displays text.",
                "Correct!",
                "No. scanf only reads.",
                "No. scanf does not stop execution."
        ));

        // ---- COURSE 8 + QUIZ C8 ----
        list.add(new CourseItem("IF / ELSE Conditions", getString(R.string.cours8_C)));

        list.add(new CourseItem("Quiz C8",
                "What is the purpose of IF / ELSE?",
                "A. To repeat actions",
                "B. To store values",
                "C. To choose between actions based on a condition",
                "D. To declare variables",
                "C. To choose between actions based on a condition",
                "No. Repetition uses loops.",
                "Correct!",
                "No. IF/ELSE does not store anything.",
                "No. Variables require type declaration."
        ));

        // ---- COURSE 9 + QUIZ C9 ----
        list.add(new CourseItem("SWITCH / CASE", getString(R.string.cours9_C)));

        list.add(new CourseItem("Quiz C9",
                "When should you use SWITCH / CASE?",
                "A. For choosing between many options based on one variable",
                "B. To repeat code",
                "C. To store many numbers",
                "D. To compare only two variables",
                "A. For choosing between many options…",
                "No. SWITCH is not a loop.",
                "Correct!",
                "No. Arrays store numbers.",
                "No. SWITCH is for more than two choices."
        ));

        // ---- COURSE 10 + QUIZ C10 ----
        list.add(new CourseItem("FOR Loop", getString(R.string.cours10_C)));

        list.add(new CourseItem("Quiz C10",
                "When do we use a FOR loop?",
                "A. When repeating a fixed number of times",
                "B. When running once",
                "C. When choosing options",
                "D. When reading input",
                "A. When repeating a fixed number of times",
                "Correct!",
                "No. No loop is needed for one execution.",
                "No. Choices use IF/ELSE or SWITCH.",
                "No. scanf reads input."
        ));

        // ---- COURSE 11 + QUIZ C11 ----
        list.add(new CourseItem("WHILE Loop", getString(R.string.cours11_C)));

        list.add(new CourseItem("Quiz C11",
                "What is the key feature of a WHILE loop?",
                "A. Repeats as long as condition is true",
                "B. Always runs once",
                "C. Declares variables automatically",
                "D. Sorts numbers",
                "A. Repeats as long as condition is true",
                "Correct!",
                "No. WHILE may run zero times.",
                "No. You must declare variables manually.",
                "No. Sorting is unrelated."
        ));

        // ---- COURSE 12 + QUIZ C12 ----
        list.add(new CourseItem("DO…WHILE Loop", getString(R.string.cours12_C)));

        list.add(new CourseItem("Quiz C12",
                "What is special about DO…WHILE?",
                "A. It never stops",
                "B. It always runs at least once",
                "C. It checks condition before running",
                "D. It works only with numbers",
                "B. It always runs at least once",
                "No. It will stop with a false condition.",
                "Correct!",
                "No. DO…WHILE checks after running.",
                "No. It works with all instructions."
        ));

        // ---- COURSE 13 + QUIZ C13 ----
        list.add(new CourseItem("Arrays", getString(R.string.cours13_C)));

        list.add(new CourseItem("Quiz C13",
                "What is an array?",
                "A. A single number",
                "B. A structure storing multiple values of the same type",
                "C. A loop",
                "D. A variable storing only text",
                "B. A structure storing multiple values",
                "No. That is a simple variable.",
                "Correct!",
                "No. A loop does not store values.",
                "No. Arrays store many types."
        ));

        // ---- COURSE 14 + QUIZ C14 ----
        list.add(new CourseItem("Introduction to Pointers", getString(R.string.cours14_C)));

        list.add(new CourseItem("Quiz C14",
                "What does a pointer store?",
                "A. True/false value",
                "B. A text string",
                "C. The address of another variable",
                "D. A number only",
                "C. The address of another variable",
                "No. That is a boolean.",
                "No. Strings are in char arrays.",
                "Correct!",
                "No. Pointers store addresses."
        ));

        // ---- COURSE 15 + QUIZ C15 ----
        list.add(new CourseItem("Structures", getString(R.string.cours15_C)));

        list.add(new CourseItem("Quiz C15",
                "What is a structure in C?",
                "A. A variable storing only integers",
                "B. A data type that groups different variables",
                "C. A loop",
                "D. A pointer",
                "B. A data type that groups different variables",
                "No. Structures store many types.",
                "Correct!",
                "No. That is a loop.",
                "No. A structure is not a pointer."
        ));

        // ---- COURSE 16 + QUIZ C16 ----
        list.add(new CourseItem("Linked Lists", getString(R.string.cours16_C)));

        list.add(new CourseItem("Quiz C16",
                "What is a linked list?",
                "A. A static array",
                "B. A structure where each element has a value and a pointer to the next",
                "C. A loop",
                "D. A function",
                "B. A structure with value + pointer",
                "No. Arrays cannot grow.",
                "Correct!",
                "No. It is a data structure.",
                "No. Not a function."
        ));

        // ---- COURSE 17 + QUIZ C17 ----
        list.add(new CourseItem("Functions and Procedures", getString(R.string.cours17_C)));

        list.add(new CourseItem("Quiz C17",
                "What is a subprogram?",
                "A. A small part that performs a specific task",
                "B. A variable storing many values",
                "C. A loop that never ends",
                "D. A list of random steps",
                "A. Performs a specific task",
                "Correct!",
                "No. Variables store data.",
                "No. Infinite loops are errors.",
                "No. Subprograms are not random."
        ));
    }
}
