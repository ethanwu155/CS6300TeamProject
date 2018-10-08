package edu.gatech.seclass.sdpvocabquiz;

import java.util.ArrayList;
import java.util.HashMap;

public class Application {

    ArrayList<Student> studentList;
    ArrayList<Quiz> quizList;
    HashMap<String, ArrayList<QuizEvent>> quizHistory;
    Student currentUser;

    public Application() {
        this.studentList = new ArrayList<>();
        this.quizList = new ArrayList<>();
        this.quizHistory = new HashMap<>();
    }

}
