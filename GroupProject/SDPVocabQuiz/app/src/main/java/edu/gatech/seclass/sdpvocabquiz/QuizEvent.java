package edu.gatech.seclass.sdpvocabquiz;

import java.util.ArrayList;
import java.util.Date;

public class QuizEvent {
    public Student student;
    public Quiz quiz;
    public Date timestamp;
    public ArrayList<QuestionEvent> questions;
    public Double score;

    public QuizEvent(Student student, Quiz quiz, Date timestamp, ArrayList questions, Double score) {
        this.student = student;
        this.quiz = quiz;
        this.timestamp = timestamp;
        this.questions = questions;
        this.score = score;
    }

}
