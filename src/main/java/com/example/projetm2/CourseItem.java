package com.example.projetm2;

public class CourseItem {
    private String title;
    private String courseContent;
    private boolean isCourse;

    public CourseItem(String title, String courseContent) {
        this.title = title;
        this.courseContent = courseContent;
        this.isCourse = true;
    }

    public String getTitle() {
        return title;
    }

    public String getCourseContent() {
        return courseContent;
    }

    public boolean isCourse() {
        return isCourse;
    }
}