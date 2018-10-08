package edu.gatech.seclass.sdpvocabquiz;
import java.util.ArrayList;
import java.util.Date;

public class QuizEvent {

    Student student;
    Quiz quiz;
    Date timestamp;
    ArrayList<QuestionEvent> questions;
    Double finalScore;

    public QuizEvent(Student student, Quiz quiz, Date timestamp, ArrayList questions, Double finalScore) {
        this.student = student;
        this.quiz = quiz;
        this.timestamp = timestamp;
        this.questions = questions;
        this.finalScore = finalScore;
    }

}
