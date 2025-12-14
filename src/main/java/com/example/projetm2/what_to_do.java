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
    private CourseQuizAdapter adapter;
    private List<CourseItem> courseList;

    SessionManager session;
    TextView tvUsername;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_to_do_recycler);

        // Check session
        session = new SessionManager(this);
        if (!session.isLoggedIn()) {
            startActivity(new Intent(this, log_in.class));
            finish();
            return;
        }

        // UI elements
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
        setupAlgorithmicCoursesMixed(courseList);

        GlobalContent.algoList = courseList;

        adapter = new CourseQuizAdapter(what_to_do.this, courseList,"algo");
        recyclerView.setAdapter(adapter);

        Button goToC = findViewById(R.id.btnGoToC);
        goToC.setOnClickListener(v -> {
            startActivity(new Intent(what_to_do.this, what_to_do_C.class));
        });
    }

    private void setupAlgorithmicCoursesMixed(List<CourseItem> list) {

        // -------------------------------------------------------
        // COURSE 1 + QUIZ 1
        // -------------------------------------------------------

        list.add(new CourseItem("Introduction to Algorithms", getString(R.string.cours1)));
        list.add(new CourseItem("Quiz 1",
                "What is an algorithm?",
                "A. A programming language used to write code",
                "B. A step-by-step set of instructions to solve a problem",
                "C. A computer machine that executes programs",
                "D. A list of random actions",
                "B. A step-by-step set of instructions to solve a problem",

                "No. A programming language is used to write code, not to define algorithms.",
                "Correct!",
                "No. A computer is a physical machine, not instructions.",
                "No. Random actions cannot solve a problem in a structured way."
        ));

        // -------------------------------------------------------
        // COURSE 2 + QUIZ 2
        // -------------------------------------------------------

        list.add(new CourseItem("Variables", getString(R.string.cours2)));
        list.add(new CourseItem("Quiz 2",
                "What is a variable?",
                "A. A container that stores information during algorithm execution",
                "B. A fixed value that never changes",
                "C. A calculation performed by the program",
                "D. A list of instructions to solve a problem",
                "A. A container that stores information during algorithm execution",

                "Correct!",
                "No. A variable can change, so it cannot be a fixed value.",
                "No. A calculation is an action, not a storage container.",
                "No. A list of instructions is an algorithm, not a variable."
        ));

        // -------------------------------------------------------
        // COURSE 3 + QUIZ 3
        // -------------------------------------------------------

        list.add(new CourseItem("Data Types", getString(R.string.cours3)));
        list.add(new CourseItem("Quiz 3",
                "What is the purpose of a data type?",
                "A. To limit how many variables can be created",
                "B. To sort the values alphabetically",
                "C. To tell what kind of value a variable can store",
                "D. To make the algorithm run faster",
                "C. To tell what kind of value a variable can store",

                "No. Data types do not limit quantity; they classify values.",
                "No. Sorting has nothing to do with data types.",
                "Correct!",
                "No. Data types describe value categories, not performance."
        ));

        // -------------------------------------------------------
        // COURSE 4 + QUIZ 4
        // -------------------------------------------------------

        list.add(new CourseItem("Operators", getString(R.string.cours4)));
        list.add(new CourseItem("Quiz 4",
                "What does the modulo operator (%) return?",
                "A. The remainder of a division",
                "B. The opposite of a number",
                "C. The result of multiplication",
                "D. The result of division",
                "A. The remainder of a division",

                "Correct!",
                "No. The modulo operator does not invert numbers.",
                "No. Multiplication uses the * operator.",
                "No. Division uses /, not %."
        ));

        // -------------------------------------------------------
        // COURSE 5 + QUIZ 5
        // -------------------------------------------------------

        list.add(new CourseItem("Input/Output", getString(R.string.cours5)));
        list.add(new CourseItem("Quiz 5",
                "What does the READ instruction do?",
                "A. It calculates a value",
                "B. It asks the user to enter a value",
                "C. It sorts numbers",
                "D. It displays information to the user",
                "B. It asks the user to enter a value",

                "No. READ does not perform calculations.",
                "Correct!",
                "No. Sorting is unrelated to reading input.",
                "No. Displaying output is handled by WRITE/PRINT."
        ));

        // -------------------------------------------------------
        // COURSE 6 + QUIZ 6
        // -------------------------------------------------------

        list.add(new CourseItem("IF / ELSE", getString(R.string.cours6)));
        list.add(new CourseItem("Quiz 6",
                "What is the purpose of IF / ELSE?",
                "A. To choose between two possible actions",
                "B. To store multiple values",
                "C. To create a new variable",
                "D. To repeat actions many times",
                "A. To choose between two possible actions",

                "Correct!",
                "No. IF/ELSE does not store values.",
                "No. IF/ELSE does not create variables.",
                "No. Loops are used for repetition, not IF/ELSE."
        ));

        // -------------------------------------------------------
        // COURSE 7 + QUIZ 7
        // -------------------------------------------------------

        list.add(new CourseItem("SWITCH / CASE", getString(R.string.cours7)));
        list.add(new CourseItem("Quiz 7",
                "When should SWITCH / CASE be used?",
                "A. When storing many values",
                "B. When choosing between many options",
                "C. When checking if a list is empty",
                "D. When repeating something 10 times",
                "B. When choosing between many options",

                "No. SWITCH/CASE is not used to store values.",
                "Correct!",
                "No. Checking a list is usually done with conditions.",
                "No. Repetition is handled by loops."
        ));

        // -------------------------------------------------------
        // COURSE 8 + QUIZ 8
        // -------------------------------------------------------

        list.add(new CourseItem("FOR Loop", getString(R.string.cours8)));
        list.add(new CourseItem("Quiz 8",
                "When do we use a FOR loop?",
                "A. When we want to repeat an action a fixed number of times",
                "B. When we do not know how many repetitions are needed",
                "C. When we do not want repetition",
                "D. When we want to stop immediately",
                "A. When we want to repeat an action a fixed number of times",

                "Correct!",
                "No. That’s what WHILE loops are for.",
                "No. If no repetition is needed, no loop is used.",
                "No. A loop stopping immediately is pointless."
        ));

        // -------------------------------------------------------
        // COURSE 9 + QUIZ 9
        // -------------------------------------------------------

        list.add(new CourseItem("WHILE Loop", getString(R.string.cours9)));
        list.add(new CourseItem("Quiz 9",
                "What does a WHILE loop do?",
                "A. Repeats actions as long as the condition is true",
                "B. Always displays a message",
                "C. Calculates averages",
                "D. Runs at least once before checking the condition",
                "A. Repeats actions as long as the condition is true",

                "Correct!",
                "No. Displaying messages is unrelated to WHILE.",
                "No. Calculations depend on instructions, not loop type.",
                "No. That describes a REPEAT loop."
        ));

        // -------------------------------------------------------
        // COURSE 10 + QUIZ 10
        // -------------------------------------------------------

        list.add(new CourseItem("REPEAT Loop", getString(R.string.cours10)));
        list.add(new CourseItem("Quiz 10",
                "What is special about a REPEAT loop?",
                "A. It always runs at least once before checking the condition",
                "B. It checks the condition before running",
                "C. It can only work with numbers",
                "D. It never stops",
                "A. It always runs at least once before checking the condition",

                "Correct!",
                "No. REPEAT checks conditions after execution.",
                "No. REPEAT works with all kinds of instructions.",
                "No. All loops eventually stop with a proper condition."
        ));

        // -------------------------------------------------------
        // COURSE 11 + QUIZ 11
        // -------------------------------------------------------

        list.add(new CourseItem("Arrays", getString(R.string.cours11)));
        list.add(new CourseItem("Quiz 11",
                "What is an array?",
                "A. A loop that repeats many times",
                "B. A condition that returns true or false",
                "C. A structure that stores multiple values of the same type",
                "D. A single number stored in memory",
                "C. A structure that stores multiple values of the same type",

                "No. That describes a loop, not a structure.",
                "No. That describes a boolean condition.",
                "Correct!",
                "No. An array holds many values, not one."
        ));

        // -------------------------------------------------------
        // COURSE 12 + QUIZ 12
        // -------------------------------------------------------

        list.add(new CourseItem("Pointers", getString(R.string.cours12)));
        list.add(new CourseItem("Quiz 12",
                "What does a pointer store?",
                "A. The address of another variable in memory",
                "B. A true or false value",
                "C. A list of numbers",
                "D. A text message",
                "A. The address of another variable in memory",

                "Correct!",
                "No. True/false values are booleans.",
                "No. A pointer does not store lists.",
                "No. Pointers store addresses, not messages."
        ));

        // -------------------------------------------------------
        // COURSE 13 + QUIZ 13
        // -------------------------------------------------------

        list.add(new CourseItem("Records", getString(R.string.cours13)));
        list.add(new CourseItem("Quiz 13",
                "What is a record (structure)?",
                "A. A data type that groups different pieces of information together",
                "B. A variable that stores only numbers",
                "C. A pointer to memory",
                "D. A loop that repeats instructions",
                "A. A data type that groups different pieces of information together",

                "Correct!",
                "No. A record can store many types, not just numbers.",
                "No. A pointer stores addresses.",
                "No. A record is not a loop."
        ));

        // -------------------------------------------------------
        // COURSE 14 + QUIZ 14
        // -------------------------------------------------------

        list.add(new CourseItem("Lists", getString(R.string.cours14)));
        list.add(new CourseItem("Quiz 14",
                "What is a linked list?",
                "A. A function that always returns a number",
                "B. A static structure that cannot grow",
                "C. A structure where each element contains a value and a pointer to the next",
                "D. A loop that repeats several actions",
                "C. A structure where each element contains a value and a pointer to the next",

                "No. A function is not a linked structure.",
                "No. Linked lists can grow dynamically.",
                "Correct!",
                "No. A loop executes repeatedly; it is not a data structure."
        ));

        // -------------------------------------------------------
        // COURSE 15 + QUIZ 15
        // -------------------------------------------------------

        list.add(new CourseItem("Subprograms", getString(R.string.cours15)));
        list.add(new CourseItem("Quiz 15",
                "What is a subprogram?",
                "A. A small part of an algorithm that performs a specific task",
                "B. A variable that stores many values",
                "C. A loop that never ends",
                "D. A list of random steps",
                "A. A small part of an algorithm that performs a specific task",

                "Correct!",
                "No. A variable stores data, not tasks.",
                "No. An infinite loop is not a subprogram.",
                "No. Random steps do not perform a defined task."
        ));

        // -------------------------------------------------------
        // COURSE 16 + QUIZ 16
        // -------------------------------------------------------

        list.add(new CourseItem("Problem-Solving", getString(R.string.cours16)));
        list.add(new CourseItem("Quiz 16",
                "What is pseudocode?",
                "A. A way to write algorithms using human-readable steps",
                "B. A real programming language",
                "C. A type of loop",
                "D. A memory structure",
                "A. A way to write algorithms using human-readable steps",

                "Correct!",
                "No. Pseudocode is not executable code.",
                "No. Pseudocode does not repeat instructions by itself.",
                "No. It does not store data; it describes logic."
        ));

        // -------------------------------------------------------
        // COURSE 17 + QUIZ 17
        // -------------------------------------------------------

        list.add(new CourseItem("Exercises", getString(R.string.cours17)));
        list.add(new CourseItem("Quiz 17",
                "What does the algorithm for finding the maximum of two numbers do?",
                "A. It always returns the second number",
                "B. It adds the two numbers together",
                "C. It compares the two numbers and returns the larger one",
                "D. It sorts the numbers",
                "C. It compares the two numbers and returns the larger one",

                "No. The second number is not always the maximum.",
                "No. Adding the numbers does not find the maximum.",
                "Correct!",
                "No. Sorting is unnecessary for only two values."
        ));

        // -------------------------------------------------------
        // COURSE 18 + QUIZ 18
        // -------------------------------------------------------

        list.add(new CourseItem("Sorting", getString(R.string.cours18)));
        list.add(new CourseItem("Quiz 18",
                "What is the main idea of Bubble Sort?",
                "A. Compare and swap adjacent elements until the list is sorted",
                "B. Remove the biggest value only",
                "C. Always pick the smallest element first",
                "D. Sort the list in one single pass",
                "A. Compare and swap adjacent elements until the list is sorted",

                "Correct!",
                "No. Bubble sort does not remove elements; it swaps them.",
                "No. That describes selection sort, not bubble sort.",
                "No. Bubble sort needs many passes, not just one."
        ));
    }
}
