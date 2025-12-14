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
    private List<CourseItem> mixedList; // course + quiz alternating
    private int unitCount;               // number of rows
    private String track;                // "c" or "algo"

    public CourseQuizAdapter(Context context, List<CourseItem> mixedList, String track) {
        this.context = context;
        this.mixedList = mixedList;
        this.unitCount = mixedList.size() / 2;
        this.track = track;
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
        int courseIndex = position * 2;   // even index
        int quizIndex = courseIndex + 1;  // odd index

        CourseItem courseItem = mixedList.get(courseIndex);
        CourseItem quizItem = mixedList.get(quizIndex);

        holder.btnCourse.setText(courseItem.getTitle());
        holder.btnQuiz.setText(quizItem.getTitle());

        // Open course screen
        holder.btnCourse.setOnClickListener(v -> {
            Intent intent = new Intent(context, cours.class);
            intent.putExtra("index", courseIndex);
            intent.putExtra("track", track);   // <<< pass track
            context.startActivity(intent);
        });

        // Open quiz screen
        holder.btnQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(context, quizz.class);
            intent.putExtra("index", quizIndex);
            intent.putExtra("track", track);   // <<< pass track
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return unitCount;
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
