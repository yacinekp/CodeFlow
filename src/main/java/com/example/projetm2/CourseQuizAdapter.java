package com.example.projetm2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseQuizAdapter extends RecyclerView.Adapter<CourseQuizAdapter.ViewHolder> {

    private Context context;
    private List<CourseItem> mixedList; // This is masterList: course, quiz, course, quiz...
    private int unitCount; // Number of rows to display

    public CourseQuizAdapter(Context context, List<CourseItem> mixedList) {
        this.context = context;
        this.mixedList = mixedList;
        this.unitCount = mixedList.size() / 2; // One row per course+quiz pair
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_course_quiz, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Convert row index → actual indices in mixedList
        int courseIndex = position * 2;     // Even index
        int quizIndex = courseIndex + 1;    // Odd index

        CourseItem courseItem = mixedList.get(courseIndex);
        CourseItem quizItem = mixedList.get(quizIndex);

        // Set button text
        holder.btnCourse.setText(courseItem.getTitle());
        holder.btnQuiz.setText(quizItem.getTitle());

        // Open course screen
        holder.btnCourse.setOnClickListener(v -> {
            Intent intent = new Intent(context, cours.class);
            intent.putExtra("index", courseIndex); // MATCHED WITH masterList
            context.startActivity(intent);
        });

        // Open quiz screen
        holder.btnQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(context, quizz.class);
            intent.putExtra("index", quizIndex); // MATCHED WITH masterList
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return unitCount; // One row per course+quiz pair
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button btnCourse, btnQuiz;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnCourse = itemView.findViewById(R.id.btnCourse);
            btnQuiz = itemView.findViewById(R.id.btnQuiz);
        }
    }
}
