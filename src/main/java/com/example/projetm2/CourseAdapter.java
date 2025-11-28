package com.example.projetm2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<CourseItem> courseList;
    private boolean isAlgorithmic;

    public CourseAdapter(List<CourseItem> courseList, boolean isAlgorithmic) {
        this.courseList = courseList;
        this.isAlgorithmic = isAlgorithmic;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course_quiz, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        CourseItem item = courseList.get(position);

        holder.btnCourse.setText(item.getTitle());
        holder.btnQuiz.setText("Quizz " + (position + 1));

        holder.btnCourse.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), cours.class);
            intent.putExtra("cours", item.getCourseContent());
            v.getContext().startActivity(intent);
        });

        holder.btnQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), quizz.class);
            setupQuizData(intent, position);
            v.getContext().startActivity(intent);
        });
    }

    private void setupQuizData(Intent intent, int position) {
        if (isAlgorithmic) {
            switch (position) {
                case 0:
                    intent.putExtra("question", "What is an algorithm?");
                    intent.putExtra("p1", "A. A programming language used to write code");
                    intent.putExtra("p2", "B. A step-by-step set of instructions to solve a problem");
                    intent.putExtra("p3", "C. A computer machine that executes programs");
                    intent.putExtra("p4", "D. A list of random actions");
                    intent.putExtra("correct", "B. A step-by-step set of instructions to solve a problem");
                    break;
                case 1:
                    intent.putExtra("question", "What is a variable?");
                    intent.putExtra("p1", "A. A container that stores information during algorithm execution");
                    intent.putExtra("p2", "B. A fixed value that never changes");
                    intent.putExtra("p3", "C. A calculation performed by the program");
                    intent.putExtra("p4", "D. A list of instructions to solve a problem");
                    intent.putExtra("correct", "A. A container that stores information during algorithm execution");
                    break;
                case 2:
                    intent.putExtra("question", "What is the purpose of a data type?");
                    intent.putExtra("p1", "A. To limit how many variables can be created");
                    intent.putExtra("p2", "B. To sort the values alphabetically");
                    intent.putExtra("p3", "C. To tell what kind of value a variable can store");
                    intent.putExtra("p4", "D. To make the algorithm run faster");
                    intent.putExtra("correct", "C. To tell what kind of value a variable can store");
                    break;
                case 3:
                    intent.putExtra("question", "What does the modulo operator (%) return?");
                    intent.putExtra("p1", "A. The remainder of a division");
                    intent.putExtra("p2", "B. The opposite of a number");
                    intent.putExtra("p3", "C. The result of multiplication");
                    intent.putExtra("p4", "D. The result of division");
                    intent.putExtra("correct", "A. The remainder of a division");
                    break;
                case 4:
                    intent.putExtra("question", "What does the READ instruction do?");
                    intent.putExtra("p1", "A. It calculates a value");
                    intent.putExtra("p2", "B. It asks the user to enter a value");
                    intent.putExtra("p3", "C. It sorts numbers");
                    intent.putExtra("p4", "D. It displays information to the user");
                    intent.putExtra("correct", "B. It asks the user to enter a value");
                    break;
                case 5:
                    intent.putExtra("question", "What is the purpose of IF / ELSE?");
                    intent.putExtra("p1", "A. To choose between two possible actions");
                    intent.putExtra("p2", "B. To store multiple values");
                    intent.putExtra("p3", "C. To create a new variable");
                    intent.putExtra("p4", "D. To repeat actions many times");
                    intent.putExtra("correct", "A. To choose between two possible actions");
                    break;
                case 6:
                    intent.putExtra("question", "When should SWITCH / CASE be used?");
                    intent.putExtra("p1", "A. When storing many values");
                    intent.putExtra("p2", "B. When choosing between many options");
                    intent.putExtra("p3", "C. When checking if a list is empty");
                    intent.putExtra("p4", "D. When repeating something 10 times");
                    intent.putExtra("correct", "B. When choosing between many options");
                    break;
                case 7:
                    intent.putExtra("question", "When do we use a FOR loop?");
                    intent.putExtra("p1", "A. When we want to repeat an action a fixed number of times");
                    intent.putExtra("p2", "B. When we do not know how many repetitions are needed");
                    intent.putExtra("p3", "C. When we do not want repetition");
                    intent.putExtra("p4", "D. When we want to stop immediately");
                    intent.putExtra("correct", "A. When we want to repeat an action a fixed number of times");
                    break;
                case 8:
                    intent.putExtra("question", "What does a WHILE loop do?");
                    intent.putExtra("p1", "A. Repeats actions as long as the condition is true");
                    intent.putExtra("p2", "B. Always displays a message");
                    intent.putExtra("p3", "C. Calculates averages");
                    intent.putExtra("p4", "D. Runs at least once before checking the condition");
                    intent.putExtra("correct", "A. Repeats actions as long as the condition is true");
                    break;
                case 9:
                    intent.putExtra("question", "What is special about a REPEAT loop?");
                    intent.putExtra("p1", "A. It always runs at least once before checking the condition");
                    intent.putExtra("p2", "B. It checks the condition before running");
                    intent.putExtra("p3", "C. It can only work with numbers");
                    intent.putExtra("p4", "D. It never stops");
                    intent.putExtra("correct", "A. It always runs at least once before checking the condition");
                    break;
                case 10:
                    intent.putExtra("question", "What is an array?");
                    intent.putExtra("p1", "A. A loop that repeats many times");
                    intent.putExtra("p2", "B. A condition that returns true or false");
                    intent.putExtra("p3", "C. A structure that stores multiple values of the same type");
                    intent.putExtra("p4", "D. A single number stored in memory");
                    intent.putExtra("correct", "C. A structure that stores multiple values of the same type");
                    break;
                case 11:
                    intent.putExtra("question", "What does a pointer store?");
                    intent.putExtra("p1", "A. The address of another variable in memory");
                    intent.putExtra("p2", "B. A true or false value");
                    intent.putExtra("p3", "C. A list of numbers");
                    intent.putExtra("p4", "D. A text message");
                    intent.putExtra("correct", "A. The address of another variable in memory");
                    break;
                case 12:
                    intent.putExtra("question", "What is a record (structure)?");
                    intent.putExtra("p1", "A. A data type that groups different pieces of information together");
                    intent.putExtra("p2", "B. A variable that stores only numbers");
                    intent.putExtra("p3", "C. A pointer to memory");
                    intent.putExtra("p4", "D. A loop that repeats instructions");
                    intent.putExtra("correct", "A. A data type that groups different pieces of information together");
                    break;
                case 13:
                    intent.putExtra("question", "What is a linked list?");
                    intent.putExtra("p1", "A. A function that always returns a number");
                    intent.putExtra("p2", "B. A static structure that cannot grow");
                    intent.putExtra("p3", "C. A structure where each element contains a value and a pointer to the next");
                    intent.putExtra("p4", "D. A loop that repeats several actions");
                    intent.putExtra("correct", "C. A structure where each element contains a value and a pointer to the next");
                    break;
                case 14:
                    intent.putExtra("question", "What is a subprogram?");
                    intent.putExtra("p1", "A. A small part of an algorithm that performs a specific task");
                    intent.putExtra("p2", "B. A variable that stores many values");
                    intent.putExtra("p3", "C. A loop that never ends");
                    intent.putExtra("p4", "D. A list of random steps");
                    intent.putExtra("correct", "A. A small part of an algorithm that performs a specific task");
                    break;
                case 15:
                    intent.putExtra("question", "What is pseudocode?");
                    intent.putExtra("p1", "A. A way to write algorithms using human-readable steps");
                    intent.putExtra("p2", "B. A real programming language");
                    intent.putExtra("p3", "C. A type of loop");
                    intent.putExtra("p4", "D. A memory structure");
                    intent.putExtra("correct", "A. A way to write algorithms using human-readable steps");
                    break;
                case 16:
                    intent.putExtra("question", "What does the algorithm for finding the maximum of two numbers do?");
                    intent.putExtra("p1", "A. It always returns the second number");
                    intent.putExtra("p2", "B. It adds the two numbers together");
                    intent.putExtra("p3", "C. It compares the two numbers and returns the larger one");
                    intent.putExtra("p4", "D. It sorts the numbers");
                    intent.putExtra("correct", "C. It compares the two numbers and returns the larger one");
                    break;
                case 17:
                    intent.putExtra("question", "What is the main idea of Bubble Sort?");
                    intent.putExtra("p1", "A. Compare and swap adjacent elements until the list is sorted");
                    intent.putExtra("p2", "B. Remove the biggest value only");
                    intent.putExtra("p3", "C. Always pick the smallest element first");
                    intent.putExtra("p4", "D. Sort the list in one single pass");
                    intent.putExtra("correct", "A. Compare and swap adjacent elements until the list is sorted");
                    break;
            }
        } else {
            switch (position) {
                case 0:
                    intent.putExtra("question", "Why is C a popular programming language?");
                    intent.putExtra("p1", "A. It is slow but easy to use");
                    intent.putExtra("p2", "B. It is close to the hardware, fast, and efficient");
                    intent.putExtra("p3", "C. It cannot be used for software development");
                    intent.putExtra("p4", "D. It only works on Windows");
                    intent.putExtra("correct", "B. It is close to the hardware, fast, and efficient");
                    break;
                case 1:
                    intent.putExtra("question", "What is the role of a C compiler?");
                    intent.putExtra("p1", "A. It writes code for you automatically");
                    intent.putExtra("p2", "B. It translates your C code into a program your computer can run");
                    intent.putExtra("p3", "C. It stores variables in memory");
                    intent.putExtra("p4", "D. It checks the internet for solutions");
                    intent.putExtra("correct", "B. It translates your C code into a program your computer can run");
                    break;
                case 2:
                    intent.putExtra("question", "What is the main function in a C program?");
                    intent.putExtra("p1", "A. A section to store variables only");
                    intent.putExtra("p2", "B. The entry point where program execution starts");
                    intent.putExtra("p3", "C. A loop that repeats instructions");
                    intent.putExtra("p4", "D. A library that prints text");
                    intent.putExtra("correct", "B. The entry point where program execution starts");
                    break;
                case 3:
                    intent.putExtra("question", "What is true about variables in C?");
                    intent.putExtra("p1", "A. They store information and can change during program execution");
                    intent.putExtra("p2", "B. They are always constant and cannot change");
                    intent.putExtra("p3", "C. They are only used to write text on the screen");
                    intent.putExtra("p4", "D. They are loops that repeat instructions");
                    intent.putExtra("correct", "A. They store information and can change during program execution");
                    break;
                case 4:
                    intent.putExtra("question", "What is the purpose of data types in C?");
                    intent.putExtra("p1", "A. To tell the computer how much memory a variable needs, what type of value it stores, and allowed operations");
                    intent.putExtra("p2", "B. To make programs run without a compiler");
                    intent.putExtra("p3", "C. To store multiple values in one variable automatically");
                    intent.putExtra("p4", "D. To create new operators");
                    intent.putExtra("correct", "A. To tell the computer how much memory a variable needs, what type of value it stores, and allowed operations");
                    break;
                case 5:
                    intent.putExtra("question", "What does the modulo operator (%) return?");
                    intent.putExtra("p1", "A. The opposite of a number");
                    intent.putExtra("p2", "B. The remainder of a division");
                    intent.putExtra("p3", "C. The result of addition");
                    intent.putExtra("p4", "D. The number of variables");
                    intent.putExtra("correct", "B. The remainder of a division");
                    break;
                case 6:
                    intent.putExtra("question", "What does the scanf function do?");
                    intent.putExtra("p1", "A. Displays text on the screen");
                    intent.putExtra("p2", "B. Reads a value entered by the user");
                    intent.putExtra("p3", "C. Performs calculations");
                    intent.putExtra("p4", "D. Stops the program");
                    intent.putExtra("correct", "B. Reads a value entered by the user");
                    break;
                case 7:
                    intent.putExtra("question", "What is the purpose of IF / ELSE?");
                    intent.putExtra("p1", "A. To repeat actions many times");
                    intent.putExtra("p2", "B. To store multiple values");
                    intent.putExtra("p3", "C. To choose between two or more actions based on a condition");
                    intent.putExtra("p4", "D. To declare variables");
                    intent.putExtra("correct", "C. To choose between two or more actions based on a condition");
                    break;
                case 8:
                    intent.putExtra("question", "When should you use SWITCH / CASE?");
                    intent.putExtra("p1", "A. When choosing between many options based on a single variable");
                    intent.putExtra("p2", "B. When you want to repeat code multiple times");
                    intent.putExtra("p3", "C. When storing many numbers");
                    intent.putExtra("p4", "D. When comparing two variables only");
                    intent.putExtra("correct", "A. When choosing between many options based on a single variable");
                    break;
                case 9:
                    intent.putExtra("question", "When do we use a FOR loop?");
                    intent.putExtra("p1", "A. When we want to repeat an action a fixed number of times");
                    intent.putExtra("p2", "B. When we want to run a loop only once");
                    intent.putExtra("p3", "C. When we want to choose between options");
                    intent.putExtra("p4", "D. When reading input from the user only");
                    intent.putExtra("correct", "A. When we want to repeat an action a fixed number of times");
                    break;
                case 10:
                    intent.putExtra("question", "What is the key feature of a WHILE loop?");
                    intent.putExtra("p1", "A. Repeats actions as long as the condition is true");
                    intent.putExtra("p2", "B. Always runs exactly once");
                    intent.putExtra("p3", "C. Declares variables automatically");
                    intent.putExtra("p4", "D. Sorts numbers");
                    intent.putExtra("correct", "A. Repeats actions as long as the condition is true");
                    break;
                case 11:
                    intent.putExtra("question", "What is special about a DO…WHILE loop?");
                    intent.putExtra("p1", "A. It never stops");
                    intent.putExtra("p2", "B. It always runs at least once before checking the condition");
                    intent.putExtra("p3", "C. It checks the condition before running");
                    intent.putExtra("p4", "D. It can only work with numbers");
                    intent.putExtra("correct", "B. It always runs at least once before checking the condition");
                    break;
                case 12:
                    intent.putExtra("question", "What is an array in C?");
                    intent.putExtra("p1", "A. A single number stored in memory");
                    intent.putExtra("p2", "B. A structure that stores multiple values of the same type");
                    intent.putExtra("p3", "C. A loop that repeats instructions");
                    intent.putExtra("p4", "D. A variable that stores text only");
                    intent.putExtra("correct", "B. A structure that stores multiple values of the same type");
                    break;
                case 13:
                    intent.putExtra("question", "What does a pointer store?");
                    intent.putExtra("p1", "A. A true/false value");
                    intent.putExtra("p2", "B. A text string");
                    intent.putExtra("p3", "C. The address of another variable in memory");
                    intent.putExtra("p4", "D. A number only");
                    intent.putExtra("correct", "C. The address of another variable in memory");
                    break;
                case 14:
                    intent.putExtra("question", "What is a structure (record) in C?");
                    intent.putExtra("p1", "A. A variable that stores only integers");
                    intent.putExtra("p2", "B. A data type that groups different variables together");
                    intent.putExtra("p3", "C. A loop that repeats instructions");
                    intent.putExtra("p4", "D. A pointer to a variable");
                    intent.putExtra("correct", "B. A data type that groups different variables together");
                    break;
                case 15:
                    intent.putExtra("question", "What is a linked list?");
                    intent.putExtra("p1", "A. A static array");
                    intent.putExtra("p2", "B. A structure where each element contains a value and a pointer to the next");
                    intent.putExtra("p3", "C. A loop that repeats several actions");
                    intent.putExtra("p4", "D. A function that returns numbers");
                    intent.putExtra("correct", "B. A structure where each element contains a value and a pointer to the next");
                    break;
                case 16:
                    intent.putExtra("question", "What is a subprogram in C?");
                    intent.putExtra("p1", "A. A small part of a program that performs a specific task");
                    intent.putExtra("p2", "B. A variable that stores many values");
                    intent.putExtra("p3", "C. A loop that never ends");
                    intent.putExtra("p4", "D. A list of random steps");
                    intent.putExtra("correct", "A. A small part of a program that performs a specific task");
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        Button btnCourse, btnQuiz;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            btnCourse = itemView.findViewById(R.id.btnCourse);
            btnQuiz = itemView.findViewById(R.id.btnQuiz);
        }
    }
}