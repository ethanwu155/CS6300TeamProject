package edu.gatech.seclass.sdpvocabquiz;

import java.util.ArrayList;
import java.util.Date;

public class QuizEvent {
    public Student student;
    public Quiz quiz;
    public Date timestamp;
    public ArrayList<QuestionEvent> questions = new ArrayList<>();
    public Double score;

    public QuizEvent(Student student, Quiz quiz, Date timestamp, ArrayList questions) {
        this.student = student;
        this.quiz = quiz;
        this.timestamp = timestamp;
        this.questions.addAll(questions);
        this.score = 0.0;
    }

}
