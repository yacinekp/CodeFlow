package com.example.projetm2;

public class CourseItem {
    private boolean isQuiz;
    private String title;

    // Lesson content
    private String content;

    // Quiz fields
    private String question;
    private String p1, p2, p3, p4;
    private String correct;
    private String c1, c2, c3, c4;

    // Lesson constructor
    public CourseItem(String title, String content) {
        this.title = title;
        this.content = content;
        this.isQuiz = false;
    }

    // Quiz constructor
    public CourseItem(String title,
                      String question,
                      String p1, String p2, String p3, String p4,
                      String correct,
                      String c1, String c2, String c3, String c4) {
        this.title = title;
        this.question = question;
        this.p1 = p1; this.p2 = p2; this.p3 = p3; this.p4 = p4;
        this.correct = correct;
        this.c1 = c1; this.c2 = c2; this.c3 = c3; this.c4 = c4;
        this.isQuiz = true;
    }

    public boolean isQuiz() { return isQuiz; }
    public String getTitle() { return title; }

    // Lesson getters
    public String getContent() { return content; }

    // Quiz getters
    public String getQuestion() { return question; }
    public String getP1() { return p1; }
    public String getP2() { return p2; }
    public String getP3() { return p3; }
    public String getP4() { return p4; }
    public String getCorrect() { return correct; }
    public String getC1() { return c1; }
    public String getC2() { return c2; }
    public String getC3() { return c3; }
    public String getC4() { return c4; }
}
